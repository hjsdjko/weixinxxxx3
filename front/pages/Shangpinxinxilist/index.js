
import {config} from '../../request/config'
import {request} from '../../request/index.js'
const app = getApp()
const appG = app.globalData;
Page({
  data: {
    ShangpinxinxiList:[]
  },
  onLoad: function () {
  },
  hsgadd(){
    wx.navigateTo({
      url: '/pages/Shangpinxinxiadd/index'
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
      request({url: '/shangpinxinxiInfo'}).then(res => {
          if(res.code === '0') {
              let ShangpinxinxiList = res.data;
              ShangpinxinxiList.forEach(item => {
                if(!item.tupian || item.tupian === '[]') {
                    item.url = this.data.defaultImageUrl;
                } else {
                    let fileArr = JSON.parse(item.tupian)
                    item.url = config.baseFileUrl + fileArr[0];
                }
               
            });
              this.setData({
                ShangpinxinxiList,
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