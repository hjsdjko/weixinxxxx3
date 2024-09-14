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
      goodsId: id
    })

    // 获取数据
    this.getDetail(id);
  },
  getDetail(id) {
    request({url: '/xinwentongzhiInfo/' + id}).then(res => {
      if(res.code === '0') {
        let obj = res.data;
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
    
    request({
      url: '/xinwentongzhiInfo/'+this.data.goodsId,
      method: 'GET',
     // data: params,
      header: {
        'content-type': 'application/json' 
      }}).then(res=>{
        
        const data = res.data;
        data.dianjilv = data.dianjilv+1,
        data.dianzan_c=0;
        data.dianzan_d=0;
        request({
          url: '/xinwentongzhiInfo',
          data:data,
          method: 'PUT',
         // data: params,
          header: {
            'content-type': 'application/json' 
          }})
    })
  },

  dianzan_d: function () {
    var that = this
    let user = wx.getStorageSync('user');
    var params = {
    }
    request({
      url: '/xinwentongzhiInfo/'+this.data.goodsId,
      method: 'GET',
     // data: params,
      header: {
        'content-type': 'application/json' 
      }}).then(res=>{
        const data = res.data;
        data.dianzan_c = 0,
        data.dianzan_d = 1,
        request({
          url: '/xinwentongzhiInfo',
          data:data,
          method: 'PUT',
         // data: params,
          header: {
            'content-type': 'application/json' 
          }}).then(res=>{
            if(res.code ==0){
              wx.showToast({
                title: '点赞成功',
                mask: true,
                duration:2000
              })
              const data = that.data.obj
              this.setData({
                obj:{...data,dianzan_d:parseInt(data.dianzan_d)+1}
              })
            }
          })
    })
  },

  dianzan_c: function () {
    var that = this;
    let user = wx.getStorageSync('user');
    var params = {
    }
    request({
      url: '/xinwentongzhiInfo/'+this.data.goodsId,
      method: 'GET',
      header: {
        'content-type': 'application/json' 
      }}).then(res=>{
        const data = res.data;
        data.dianzan_c = 1,
        data.dianzan_d = 0,
        request({
          url: '/xinwentongzhiInfo',
          data:data,
          method: 'PUT',
          header: {
            
            'content-type': 'application/json' 
          }}).then(res=>{
            if(res.code == 0){
              wx.showToast({
                title: '反对成功',
                mask: true,
                duration:2000,
              })
              const data = that.data.obj
              this.setData({
                obj:{...data,dianzan_c:parseInt(data.dianzan_c)+1}
              })
            }
          })
    })
  },
})