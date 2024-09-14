import {request} from "../../request/index.js";
Page({

  /**
   * 页面的初始数据
   */
  data: {
    status: "",
    dataList: []
  },

  onLoad: function (options) {
    this.getCollectData();
  },

  getCollectData() {
    let user = wx.getStorageSync('user');
    let url = "/collectInfo/page/front2?pageNum=1&pageSize=100&userId=" + user.id;
    request({url: url}).then(res => {
      if (res.code === "0") {
        let list = res.data.list;
        console.log(list)
        list.forEach((item, index) => {
          let cheliangxinxiInfo = item.cheliangxinxiInfoList[0];
          let imgSrc = "../../imgs/default.png";
          if (cheliangxinxiInfo.tupian) {
            let fileIds = JSON.parse(cheliangxinxiInfo.tupian);
            if (fileIds.length) {
                imgSrc = 'http://localhost:8888/files/download/' + fileIds[0];
            }
          }
          item.url = imgSrc;
        })
        this.setData({
          dataList: list
        })
      } else {
        console.log(res.data.msg)
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
          request({url:"/collectInfo/" + orderId, method: "DELETE"}).then(res => {
            if (res.code == "0") {
              wx.showToast({
                title: '删除成功',
              })
              _this.getCollectData();
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