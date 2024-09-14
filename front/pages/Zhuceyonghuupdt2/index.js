import {request} from "../../request/index.js";
import {config} from "../../request/config.js";
Page({
  data: {
	zhanghao: '',		mima: '',		xingming: '',		xingbiearray: ['男','女'],		shouji: '',		shenfenzheng: '',		source: '/imgs/addimg.png',		zhaopianx:'',		beizhu: '',		
  },

  zhanghaoInput: function (e) {
    this.setData({
      zhanghao: e.detail.value,
    })
  },		mimaInput: function (e) {
    this.setData({
      mima: e.detail.value,
    })
  },		xingmingInput: function (e) {
    this.setData({
      xingming: e.detail.value,
    })
  },		bindPickerChangexingbie: function (e) {
    var indexs = e.detail.value;
    this.setData({
      xingbie: this.data.xingbiearray[indexs]
    })
  },		shoujiInput: function (e) {
    this.setData({
      shouji: e.detail.value,
    })
  },		shenfenzhengInput: function (e) {
    this.setData({
      shenfenzheng: e.detail.value,
    })
  },						

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var user = wx.getStorageSync('user');
    this.setData({
      user,
    })
   this.getUserInfo();
  },
  //获取个人信息资料
  getUserInfo:function(){
    var that = this;
    var user = wx.getStorageSync('user');
    request({
      url: '/zhuceyonghuInfo/'+user.userId,
      method: 'GET',
      header: {
        'content-type': 'application/json' 
      }
    }).then(res=>{
      if (res.code == 0) {
        that.setData({
			zhanghao: res.data.zhanghao,    mima: res.data.mima,    xingming: res.data.xingming,    xingbie: res.data.xingbie,    shouji: res.data.shouji,    shenfenzheng: res.data.shenfenzheng,    avatarUrl: config.baseFileUrl+JSON.parse(res.data.zhaopian),    zhaopian: res.data.zhaopian,    beizhu: res.data.beizhu,    
          
        })
      } else {
        wx.showToast({
          title: res.msg,
          icon: 'none',
        })
      }
    })
  },
  
      bindPickerChangexingbie: function (e) {
    var indexs = e.detail.value;
    this.setData({
      index: indexs
    })
	if (indexs == "0") {this.setData({ xingbie: '男' })} if (indexs == 1) { this.setData({ xingbie: '女'})}
  },    
  
 
   /**
   * 上传图片
   */
  uploadimg: function () {
    var that = this;
    wx.chooseImage({ //从本地相册选择图片或使用相机拍照
      count: 1, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        //console.log(res)
        //前台显示
        that.setData({
          source: res.tempFilePaths
        })
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        var tempFilePaths = res.tempFilePaths
        wx.uploadFile({
          url:'http://localhost:8888/files/upload',
          filePath: tempFilePaths[0],
          name: 'file',
          header: {
            'content-type': 'application/json', // 默认值
          },
          success: function (res) {
            var queryBean = JSON.parse(res.data);
            var fileurl = queryBean.data.id;
            that.setData({
              avatarUrl: config.baseFileUrl+fileurl,
              zhaopian: JSON.stringify([fileurl]),
              status:'上传成功'
             });
          }
        })
      }
    })
  },
  // 修改资料
  logins: function () {
    var user = wx.getStorageSync('user');
    var userId = user.userId
    var params = {
      id:userId,
      zhanghao: this.data.zhanghao,      xingming: this.data.xingming,      xingbie: this.data.xingbie,      shouji: this.data.shouji,      shenfenzheng: this.data.shenfenzheng,      zhaopian: this.data.zhaopian,      beizhu: this.data.beizhu,      
    }
    request({
      url:'/zhuceyonghuInfo',
      method: 'PUT',
      data: params,
      header: {
        'content-type': 'application/json' 
      }
    }).then(res=>{
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