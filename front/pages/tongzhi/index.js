import {request} from '../../request/index.js'
import {config} from '../../request/config'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    obj: {},
    goodsId: 0,
    commentList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const id = options.id;
    this.setData({
      userTextId: id
    })
    // 获取数据
    this.getDetail(id);
  },
  getDetail(id) {
    request({url: '/xiaoyuantongzhiInfo/' + id}).then(res => {
      if(res.code === '0') {
        let obj = res.data;
        this.setData({
          obj
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