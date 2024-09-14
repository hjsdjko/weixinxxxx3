import {request} from '../../request/index.js'

Page({
  data: {
    defaultImageUrl: '../../imgs/default.png',
    cart: [ ],
    totalPrice: 0,
    totalNum: 0,
    dianhua:"",
    dizhi:'',
    showPayPwdInput: false,  //是否展示密码输入层
    pwdVal: '',  //输入的密码
    payFocus: true, //文本框焦点
    addressInfo:{
    }
  },
  onShow(e) {
    let user = wx.getStorageSync('user');
    this.getyonghu(user.userId);
    this.getCartInfo();
  },
  getyonghu(id) {
    
    request({url: '/zhuceyonghuInfo/'+id}).then(res => {
      if(res.code === '0') {
        let objuser = res.data;
        this.setData({
       
    
        })
      } else {
        wx.showToast({
          title: res.msg,
          icon: 'none'
        })
      }
    })
  },
  showInputLayer: function(){
    this.setData({ showPayPwdInput: true, payFocus: true });
  },
   /**
   * 获取焦点
   */
  getFocus: function(){
    this.setData({ payFocus: true });
  },
  inputPwd: function(e){
    this.setData({ pwdVal: e.detail.value });
    if (e.detail.value.length >= 6){
      this.hidePayLayer();
        let user = wx.getStorageSync("user");
    if(this.data.dianhua){
    if(this.data.dizhi){
      this.data.cart.forEach(item=>{
        let data = {
          shangpinbianhao:item.shangpinbianhao,
          shangpinmingcheng:item.shangpinmingcheng,
          shangpinleibie:item.shangpinleibie,
          kucun:item.kucun-item.count,
          xiaoliang:item.xiaoliang+item.count,
          jiage:item.jiage,
          goumaishuliang:item.count,
          goumaijine:item.count*item.jiage,
          goumairen:user.id,
          dianhua:this.data.dianhua,
          dizhi:this.data.dizhi,
          iszf:'是',
          mima:this.data.pwdVal
        };
        request({url: '/goumaiInfo/goumai', method: 'POST', data:data}).then(res => {
          if (res.code === '0') {
          wx.redirectTo({
            url: '/pages/Goumailist2/index'
          });
        }else{
          wx.showToast({
            title: res.msg,
            icon: 'error'
          })
        }
        })
      })
    }else{
      wx.showToast({
        title:"请填写地址",
        icon: "none"
      })
      return;
    }
  }else{
    wx.showToast({
      title:"请填写电话",
      icon: "none"
    })
    return;
  } 
    }
  },
  hidePayLayer: function(){
    var val = this.data.pwdVal;
    this.setData({ showPayPwdInput: false, payFocus: false, pwdVal: '' })
  },
  // 菜品的选中
  getCartInfo() {
    let user = wx.getStorageSync("user");
    let url = '/pages/login/index?isTabBar=1&url=/pages/cartInfo/index';
    if (!user) {
      wx.navigateTo({
        url: url,
      })
      return;
    }
    request({url: '/cartInfo?userId=' + user.id}).then(res => {
      if (res.code === '0') {
        console.log(res.data);
        let cartList = res.data;
        let totalPrice = 0;
        let totalNum = 0;
        cartList.forEach(item => {
          totalNum += item.count;
          totalPrice += item.count * item.jiage;
          let imgSrc = this.data.defaultImageUrl;
          if (item.tupian) {
            let fileId = JSON.parse(item.tupian)[0];
            imgSrc = 'http://localhost:8888/files/download/' + fileId;
          }
          item.url = imgSrc;
        })
        this.setData({
          cart: cartList,
          totalNum: totalNum,
          totalPrice: totalPrice.toFixed(2)
        })
        console.log(this.data.cart)
      }
    })
  },
    /**
   * 选择收货地址
   */
  showAddressList: function(){
    wx.navigateTo({
      url: '../address/index',
    })
  },
  dianhuaInput(e){
    this.setData({
      dianhua: e.detail.value,
    })
  },
  nameInput(e){
    this.setData({
      dizhi: e.detail.value,
    })
  },
  // 菜品数量的编辑功能
  handleItemNumEdit(e) {
    // 1 获取传递过来的参数 
    const { operation, id } = e.currentTarget.dataset;
    // 2 获取购物车数组
    let cart = this.data.cart;
    // 3 找到需要修改的菜品的索引
    const index = cart.findIndex(v => v.id === id);
    // 4 判断是否要执行删除
    if (cart[index].count === 1 && operation === -1) {
      // 4.1 弹窗提示
      wx.showModal({
        content: '您是否要删除？',
        success: (res) => {
          if (res.confirm) {
            let user = wx.getStorageSync("user");
            request({url: '/cartInfo/goods/' + user.id + '/' + id, method: 'DELETE'}).then(res => {
              if (res.code === '0') {
                let cart = this.data.cart;
                cart.splice(index, 1);
                let totalPrice = 0;
                let totalNum = 0;
                cart.forEach(item => {
                  totalNum += item.count;
                  totalPrice += item.count * item.jiage;
                })
                this.setData({
                  cart: cart,
                  totalPrice: totalPrice.toFixed(2),
                  totalNum: totalNum
                })
              } else {
                wx.showToast({
                  title: res.msg,
                  icon: 'error'
                })
              }
            })
          }
        }
      })
    } else {
      // 4  进行修改数量
      let cart = this.data.cart;
      cart[index].count += operation;
      if(cart[index].count>cart[index].kucun){
        wx.showToast({
          title:"库存不足",
          icon: "none"
        })
        return;
      }
      // 重新计算一下总价
      let totalPrice = 0;
      let totalNum = 0;
      cart.forEach(item => {
        totalNum += item.count;
        totalPrice += item.count * item.jiage;
      })
      this.setData({
        cart: cart,
        totalPrice: totalPrice.toFixed(2),
        totalNum: totalNum
      })
    }
  },
  // 点击 结算 
  handlePay(){
    if (this.data.cart.length === 0) {
      wx.showToast({
        title:"购物车空空如也~",
        icon: "none"
      })
      return;
    }
    if(!this.data.dianhua){
      wx.showToast({
        title:"请先填写电话",
        icon: "none"
      })
      return;
    }
    if(!this.data.dizhi){
      wx.showToast({
        title:"请先填写地址",
        icon: "none"
      })
      return;
    }
    this.showInputLayer()
  },
  uuid() {
    return 'xxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = Math.random() * 8 | 0,
            v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(8);
    });
  }
})