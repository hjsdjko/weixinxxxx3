
import {config} from '../../request/config'
import {request} from '../../request/index.js'
const app = getApp()
const appG = app.globalData;
Page({
  data: {
    user:{},//用户
    goodsId: 0,
    ShoucangjiluList:[]
  },
  onLoad: function () {
  },
  onLoad: function (options) {
    var user = wx.getStorageSync('user');
    const id = options.id;
    this.setData({
      user,
      goodsId: id
    })
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
      request({url: '/collectInfo/findAll/'+user.userId+'/'+user.level}).then(res => {
          if(res.code === '0') {
              let ShoucangjiluList = res.data;
              
              this.setData({
                ShoucangjiluList,
              })
          } else {
              wx.showToast({
                  title: res.msg,
                  icon: 'none'
              })
          }
      })
    },
    hsgchakan: function (e) {
      const { id } = e.currentTarget.dataset;
      const ShoucangjiluList = this.data.ShoucangjiluList
      const index = ShoucangjiluList.findIndex(v => v.id === id);
    wx.navigateTo({
      url: '/pages/'+ShoucangjiluList[index].biao+'detail/index?id='+ShoucangjiluList[index].shujuid
    });
     
    },
    hsgshanchu: function (e) {
      const { id } = e.currentTarget.dataset;
    // 2 获取购物车数组
    let ShoucangjiluList = this.data.ShoucangjiluList;
    // 3 找到需要修改的菜品的索引
      wx.showModal({
        content: '您是否要删除？',
        success: (res) => {
          if (res.confirm) {
            
            let user = wx.getStorageSync("user");
            request({url: '/collectInfo/' + id, method: 'DELETE'}).then(res => {
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