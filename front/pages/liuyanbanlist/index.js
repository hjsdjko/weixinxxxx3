// pages/reg/reg.js
import {config} from '../../request/config'
import {request} from '../../request/index.js'
const app = getApp()
const appG = app.globalData;
Page({
  data: {
    KeshixinxiList:[]
  },
  onLoad: function () {
  },
  
  onShow:function(){
    let user = wx.getStorageSync('user');
    if (!user) {
      	wx.showToast({
        title: '请先登录',
        icon: 'none'
      }) 
      wx.navigateTo({
        url: '/pages/login/index'
      });
    } 
	else {
    }
    this.getAdminTextInfoList();
    },
	
    getAdminTextInfoList() {
      let user = wx.getStorageSync('user');
      request({url: '/liuyanbanInfo'}).then(res => {
          if(res.code === '0') {
              let liulanbanlist = res.data;
              
              this.setData({
                liulanbanlist,
              })
          } else {
              wx.showToast({
                  title: res.msg,
                  icon: 'none'
              })
          }
      })
    },
    toAdd(){
      wx.navigateTo({
        url: '/pages/liuyanbanadd/index',
      })
    },
})