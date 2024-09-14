// pages/login/login.js
import {request} from "../../request/index.js";
const util = require('../../utils/util.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: { },
  // 登录 
  login(e) {
    let that = this
        wx.getUserProfile({
        desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
        success: (res) => {
            console.log(res);
            var data = {
                name:res.userInfo.nickName,
                code:res.userInfo.avatarUrl,
                nickName:res.userInfo.nickName,
                password: "123456",
                account:800000,
                avater:res.userInfo.avatarUrl
                }
            wx.request({url: 'http://localhost:8888' + '/yonghu/register', data:data, method:"POST",
                success:res => {
                    if (res.data.code === "0") {
                    that.login2(data);
                }else if(res.data.code === '2001'){
                    that.login2(data);
                } 
                }
                })
        }
        })
  },
  login2:function(userInfo){
    let data = {name:userInfo.nickName, password: 123456}
    wx.request({
        url: 'http://localhost:8888' + '/yonghu/login',
        method: 'post',
        data: data,
        success: res => {
            if (res.data.code != '0') {
                util.customModal(res.data.msg, true)
                return;
            }
            const userInfo = res.data.data;
            wx.setStorageSync('user', res.data.data);
            if(userInfo.userId == null){
                //如果没有绑定去往绑定处
                wx.setStorageSync('verify', 0);
            }else{
                //如果绑定成功则前往
                wx.setStorageSync('verify', 1);
            }
            wx.switchTab({
                url: '/pages/user/index',
            })
        },
        complete: function () {
            wx.hideLoading()
        }
    })
  },
  onLoad: function(options) {
    let url = options.url.replace("$", "?").replace("-","=");
    let isTabBar = options.isTabBar;
    this.setData({
      username: "",
      password: "",
      url: url,
      isTabBar: isTabBar
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
})