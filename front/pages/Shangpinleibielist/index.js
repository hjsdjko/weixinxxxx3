
import {config} from '../../request/config'
import {request} from '../../request/index.js'
const app = getApp()
const appG = app.globalData;
Page({
  data: {
    ShangpinleibieList:[]
  },
  onLoad: function () {
  },
  hsgadd(){
    wx.navigateTo({
      url: '/pages/Shangpinleibieadd/index'
    });
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
      request({url: '/shangpinleibieInfo'}).then(res => {
          if(res.code === '0') {
              let ShangpinleibieList = res.data;
              
              this.setData({
                ShangpinleibieList,
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