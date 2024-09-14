import {request} from "../../request/index.js";
Page({

  data: {
    goodsId: 0,
    content: ''
  },


  onLoad: function (options) {
  },

  onComment(e) {
    this.setData({
      mima: e.detail.value
    })
  },

  comment(e) {
    let user = wx.getStorageSync('user');
    let data = {id: user.id, mima: this.data.mima};
    request({url:"/yonghu/zhifu", data:data, method:"POST"}).then(res => {
      if (res.code === "0") {
        wx.showToast({
          title: '设置成功',
        })
        wx.switchTab({
          url: '/pages/user/index',
        })
      } else {
        wx.showToast({
          title: res.msg,
          icon: "none"
        })
      }
    })
  }
})