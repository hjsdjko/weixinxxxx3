// pages/reg/reg.js
// pages/login/login.js
import {request} from "../../request/index.js";
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    phone: '',
    password: '',
    email: '',
    phones: '',
    address:'',
    sex: '男',
    type:1,
    realname:'',
    array: ['男', '女'],
  },
  // 获取输入账号 
  phoneInput: function (e) {

    this.setData({
      phone: e.detail.value
    })

  },
  // 获取输入姓名
  nameInput: function (e) {

    this.setData({
      realname: e.detail.value
    })

  },
  // 获取输入邮箱
  emailInput: function (e) {

    this.setData({
      email: e.detail.value
    })

  },
  // 获取输入手机号
  phonesInput: function (e) {

    this.setData({
      phones: e.detail.value
    })

  },
   // 获取输入地址
   addressInput: function (e) {

    this.setData({
      address: e.detail.value
    })

  },

  // 获取输入密码 
  passwordInput: function (e) {
    this.setData({
      password: e.detail.value
    })
  },
  

  bindPickerChange: function (e) {
    var indexs = e.detail.value;
    this.setData({
      index: indexs
    })
    if (indexs == 0) {
      this.setData({
        sex: '男'
      })
    } else if (indexs == 1) {
      this.setData({
        sex: '女'
      })
    } else {
      this.setData({
        sex: ''
      })
    }
  },
  // 注册
  login: function () {
    if (this.data.phone.length == 0 || this.data.password.length == 0) {
      wx.showToast({
        title: '账号和密码不能为空',
        icon: 'none',
        duration: 2000
      })
      return;
    }
    if (this.data.realname.length == 0 ) {
      wx.showToast({
        title: '姓名不能为空',
        icon: 'none',
        duration: 2000
      })
      return;
    }
    if (this.data.phone.length == 0 ) {
      wx.showToast({
        title: '手机号码不能为空',
        icon: 'none',
        duration: 2000
      })
      return;
    }
    var params = {
      zhanghao: this.data.phone,
      mima: this.data.password,
      xingming: this.data.realname,
      xingbie: this.data.sex,
      shouji: this.data.phones,
    }
    request({
      url: '/user/reg.do',
      method: 'POST',
      data: params,
      header: {
        'content-type': 'application/json' 
      }}).then(res=>{
        if (res.code == 0) {
          wx.showToast({
            title: '注册成功',
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