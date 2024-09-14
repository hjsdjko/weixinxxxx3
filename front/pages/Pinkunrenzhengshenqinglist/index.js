
import {config} from '../../request/config'
import {request} from '../../request/index.js'
const app = getApp()
const appG = app.globalData;
Page({
  data: {
    PinkunrenzhengshenqingList:[]
  },
  onLoad: function () {
  },
  hsgadd(){
    wx.navigateTo({
      url: '/pages/Pinkunrenzhengshenqingadd/index'
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
      request({url: '/pinkunrenzhengshenqingInfo'}).then(res => {
          if(res.code === '0') {
              let PinkunrenzhengshenqingList = res.data;
              PinkunrenzhengshenqingList.forEach(item => {
                if(!item.pinkunzhengshu || item.pinkunzhengshu === '[]') {
                    item.url = this.data.defaultImageUrl;
                } else {
                    let fileArr = JSON.parse(item.pinkunzhengshu)
                    item.url = config.baseFileUrl + fileArr[0];
                }
               
            });
              this.setData({
                PinkunrenzhengshenqingList,
              })
          } else {
              wx.showToast({
                  title: res.msg,
                  icon: 'none'
              })
          }
      })
    },
	hsgdetail: function (e) {
      const { id } = e.currentTarget.dataset;
      wx.navigateTo({
        url: '/pages/pinkunrenzhengshenqingdetail/index?id='+id,
      });
    },
    hsgshanchu: function (e) {
      const { id } = e.currentTarget.dataset;
      wx.showModal({
        content: '您是否要删除？',
        success: (res) => {
          if (res.confirm) {
            let user = wx.getStorageSync("user");
            request({url: '/pinkunrenzhengshenqingInfo/' + id, method: 'DELETE'}).then(res => {
              if (res.code === '0') {
                wx.showToast({
                  title: '删除成功',
                  icon: 'none'
                })
                this.getAdminTextInfoList();
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
     
    },
})