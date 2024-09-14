import {request} from '../../request/index.js'
import {config} from '../../request/config'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    obj: {},
    goodsId: 0,
    swiperList: [],
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
    this.getCommentsList(1);
  },
  getDetail(id) {
    request({url: '/userTextInfo/click/' + id}).then(res => {
      if(res.code === '0') {
        let obj = res.data;
        let swiperList = [];
        if(obj.fileIds) {
          let list = JSON.parse(obj.fileIds);
          list.forEach(item => {
            let imgObj = {};
            imgObj.fileId = item;
            imgObj.imgSrc = 'http://localhost:8888/files/download/' + item;
            swiperList.push(imgObj);
          });
        }
        if (swiperList.length === 0) {
          swiperList.push({imgSrc:"../../imgs/default.png"});
          swiperList.push({imgSrc:"../../imgs/default.png"});
        }
        this.setData({
          obj,
          swiperList
        })
      } else {
        wx.showToast({
          title: res.msg,
          icon: 'none'
        })
      }
    })
  },
  leaveComment:function(){
    this.setData({
      commentFocus:true,
    })
  },
  saveComment:function(e){
    var me = this;
    var content = e.detail.value;
    let user = wx.getStorageSync('user');
    let data = {content,paperId:me.data.userTextId,userId:user.userId,level:user.level};
    request({url: '/paperCommentInfo', method: 'POST', data:data}).then(res => {
      if (res.code === '0') {
        wx.showToast({
          title:"评论成功"
        })
      } else {
        wx.showToast({
          title: res.msg,
          icon: 'error'
        })
      }
      me.getCommentsList(1);
      })
  },
  getCommentsList:function(page){
    var me = this;
    var videoId = me.data.userTextId;
    request({url: '/paperCommentInfo/all/'+videoId}).then(res => {
      if (res.code === '0') {
        var commentsList = res.data;
        var total = commentsList.length;
        commentsList.forEach(item=>{
          if(!item.avater || item.avater === '[]') {
          } else {
              let fileArr = JSON.parse(item.avater)
              item.avater = config.baseFileUrl + fileArr[0];
          }
        })
        me.setData({
          commentsList:commentsList,
          commentsPage:page,
          commentsTotalPage:Math.ceil(total/5),
        })
      } else {
        wx.showToast({
          title: res.msg,
          icon: 'error'
        })
      }
    })
  },
  onReachBottom:function(){
    console.log("aa");
    var me = this;
    var currentPage = me.data.commentsPage;
    var totalPage = me.data.commentsTotalPage;
    if(currentPage == totalPage){
      return;
    }
    var page = currentPage+1;
    me.getCommentsList(page);
  },
  handleCollect() {
    let user = wx.getStorageSync("user");
    let url = '/pages/login/index';
    if (!user) {
      wx.navigateTo({
        url: url,
      })
      return;
    }
    let data = {userId: user.userId, level: user.level, foreignId:this.data.userTextId};
    request({url:"/collectInfo", data:data, method:"POST"}).then(res => {
      if (res.code === "0") {
        wx.showToast({
          title:"收藏成功"
        })
      } else {
        wx.showToast({
          title: res.msg,
          icon: "error"
        })
      }
    })
  },
})