// pages/forgetPwd/forgetPwd.js
// pages/login/login.js
import {request} from "../../request/index.js";
const app = getApp()
const appG = app.globalData;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    password: '',
    newPass: '',
    newPass2: ''
  },
  // 获取输入密码 
  passwordInput: function (e) {
    this.setData({
      password: e.detail.value
    })
  },
  // 获取输入新密码 
  newPasswordInput: function (e) {
    this.setData({
      newPass: e.detail.value
    })
  },

  newPasswordInput2: function (e) {
    this.setData({
      newPass2: e.detail.value
    })
  },
  // 提交修改密码
  login: function () {
    if (this.data.password.length == 0 || this.data.newPass.length === 0  || this.data.newPass2.length === 0) {
      wx.showToast({
        title: '所有字段不能为空',
        icon: 'none',
        duration: 2000
      })
    } else {
      if ( this.data.newPass != this.data.newPass2 ) {
        wx.showToast({
          title: '两次密码不一致',
          icon: 'none',
          duration: 2000
        })
      }
      else
      {
      var userId = wx.getStorageSync('userId');
      var user = wx.getStorageSync('user');
      var params = {
        mima: this.data.password,
        newMima: this.data.newPass,
      }
      let url = ''
	  if(user.level == '注册用户'){    params.账号 = user.code    url = '/注册用户Info/changePassword'    }    
      request({
        url:url,
        method: 'PUT',
        data: params,
        header: {
          'content-type': 'application/json' 
        }}).then(res=>{
          if (res.code == 0) {
            wx.showToast({
              title: '修改成功',
              icon: 'none',
            })
            setTimeout(function () {
              wx.navigateBack({
                delta: 2,
              });
            }, 1000)
          } else {
            wx.showToast({
              title: res.msg,
              icon: 'none',
            })
          }
        })
      }
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})