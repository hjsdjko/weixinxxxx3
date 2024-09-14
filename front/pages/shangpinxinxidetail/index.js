import {request} from '../../request/index.js'
import {config} from '../../request/config'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    obj: {},
    goodsId: 0,
    commentList: []
  },
  
  

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let user = wx.getStorageSync('user');
    if (!user) {
      wx.showToast({
        title: '请先登录',
        icon: 'none'
      }) 
      wx.navigateTo({
        url: '/pages/login/index'
      });
    } else {
    }
    const id = options.id;
    this.setData({
      userTextId: id
    })
    // 获取数据
    this.getDetail(id);
  },

  handleCartAdd(){
    let user = wx.getStorageSync("user");
    let url = '/pages/login/index';
    if (!user) {
      wx.navigateTo({
        url: url,
      })
      return;
    }
    let data = {userId: user.id, shangpinxinxiId:this.data.obj.id, count: 1};
    request({url:"/cartInfo", data:data, method:"POST"}).then(res => {
      if (res.code === "0") {
        wx.showToast({
          title:"加入购物车成功"
        })
      } else {
        wx.showToast({
          title: res.msg,
          icon: "error"
        })
      }
    })
  },



  pl(){wx.navigateTo({url: '/pages/Nongchanpinpinglunadd/index?id='+this.data.obj.id});},



  //jixaaxnnxiu
  getDetail(id) {
    request({url: '/shangpinxinxiInfo/' + id}).then(res => {
      if(res.code === '0') {
        let obj = res.data;
			if(obj.tupian){obj.tupian = config.baseFileUrl + JSON.parse(res.data.tupian)[0]}
        
        this.setData({
          obj
        })
      } else {
        wx.showToast({
          title: res.msg,
          icon: 'none'
        })
      }
    })
  },
})