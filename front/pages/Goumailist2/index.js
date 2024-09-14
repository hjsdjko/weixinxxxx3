
import {config} from '../../request/config'
import {request} from '../../request/index.js'
const app = getApp()
const appG = app.globalData;
Page({
  data: {
    GoumaiList:[]
  },
  onLoad: function () {
  },
  hsgadd(){
    wx.navigateTo({
      url: '/pages/Goumaiadd/index'
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
      request({url: '/goumaiInfo/getByUserId/'+user.id}).then(res => {
          if(res.code === '0') {
              let GoumaiList = res.data;
              GoumaiList.forEach(item => {
                if(!item.tupian || item.tupian === '[]') {
                    item.url = this.data.defaultImageUrl;
                } else {
                    let fileArr = JSON.parse(item.tupian)
                    item.url = config.baseFileUrl + fileArr[0];
                }
              this.setData({
                GoumaiList,
              })
            })
          } else {
              wx.showToast({
                  title: res.msg,
                  icon: 'none'
              })
          }
      })
    },
    payGoods(e) {
      let orderId = e.currentTarget.dataset.id;
      let status = e.currentTarget.dataset.status;
      request({url:"/goumaiInfo/status/" + orderId + "/" + status, method:"POST"}).then(res => {
        if (res.code === "0") {
          wx.showToast({
            title: '操作成功',
          })
          this.getAdminTextInfoList();
        } else {
          wx.showToast({
            title: res.msg,
            icon: "none"
          })
        }
      })
    },
    deleteOrder(e) {
      let orderId = e.currentTarget.dataset.id;
      let _this = this;
      wx.showModal({
        title: "提示",
        content: "确定删除吗？",
        success(res) {
          if (res.confirm) {
            request({url:"/goumaiInfo/" + orderId, method: "DELETE"}).then(res => {
              if (res.code === "0") {
                wx.showToast({
                  title: '删除成功',
                })
                _this.getAdminTextInfoList();
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