import {request} from "../../request/index.js";
import { formatTime, formatDate,addTime } from '../../utils/util.js';
import {config} from '../../request/config'
const app = getApp()
const appG = app.globalData;

Page({
  data: {
  		shangpinbianhao: '',		shangpinmingcheng: '',		pingfen: '',pingfenarray: ['1','2','3','4','5'],		pingjia: '',		zhanghao: '',		xingming: '',		
		hsgid:0,
  },
  
  onShow: function (options) {
  	//xiabiaoduan1
    let user = wx.getStorageSync('user');
    if (!user) {
      wx.showToast({
        title: '请先登录',
        icon: 'none'
      }) 
      wx.navigateTo({
        url: '/pages/login/index'
      });
    } else {
    }
    this.setData({
      user,
	  
    })
  },
  
   onLoad: function (options) {
    let id = options.id
    let user = wx.getStorageSync('user');
    if (!user) {
      wx.showToast({
        title: '请先登录',
        icon: 'none'
      }) 
      wx.navigateTo({
        url: '/pages/login/index'
      });
    } else {
     	this.setData({
        hsgid: id
      })
    }
    var that = this;
    var TIME = formatTime(new Date());
    this.setData({
      user,
      
    })
	this.getDetail(id);
	this.getyonghu(user.userId);
   
  },
  
    getDetail(id) {
    request({url: '/shangpinxinxiInfo/'+id}).then(res => {
      if(res.code === '0') {
        let obj = res.data;
        this.setData({
          obj,
		  shangpinbianhao: res.data.shangpinbianhao,    shangpinmingcheng: res.data.shangpinmingcheng,    
    
        })
      } else {
        wx.showToast({
          title: res.msg,
          icon: 'none'
        })
      }
    })
  },
  getyonghu(id) {
request({url: '/zhuceyonghuInfo/'+id}).then(res => {
  if(res.code === '0') {
	let objuser = res.data;
	this.setData({
	  objuser,
	  zhanghao: objuser.zhanghao,    xingming: objuser.xingming,    

	})
  } else {
	wx.showToast({
	  title: res.msg,
	  icon: 'none'
	})
  }
})
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
            'content-type': 'multipart/form-data'
          },
          formData: {
            method: 'POST'   //请求方式
          },
          success: function (res) {
            var queryBean = JSON.parse(res.data);
            var fileurl = queryBean.data.id;
			var filename = queryBean.data.originName;
            that.setData({
              tuxpixanzdx: config.baseFileUrl+fileurl,
              tuxpixanzd: JSON.stringify([fileurl]),
              status:'上传成功'
             });
            }
        })
      }
    })
  },
  
   // 获取
   shangpinbianhaoInput: function (e) {
    this.setData({
      shangpinbianhao: e.detail.value,
    })
  },		shangpinmingchengInput: function (e) {
    this.setData({
      shangpinmingcheng: e.detail.value,
    })
  },		bindPickerChangepingfen: function (e) {
    var indexs = e.detail.value;
    this.setData({
      pingfen: this.data.pingfenarray[indexs]
    })
  },		pingjiaInput: function (e) {
    this.setData({
      pingjia: e.detail.value,
    })
  },		zhanghaoInput: function (e) {
    this.setData({
      zhanghao: e.detail.value,
    })
  },		xingmingInput: function (e) {
    this.setData({
      xingming: e.detail.value,
    })
  },		
  // add
  login: function () {
    let user = wx.getStorageSync('user');
    var params = {
		shangpinbianhao:this.data.shangpinbianhao,shangpinmingcheng:this.data.shangpinmingcheng,pingfen:this.data.pingfen,pingjia:this.data.pingjia,zhanghao:this.data.zhanghao,xingming:this.data.xingming,
    }
	
	
    request({
      url: '/nongchanpinpinglunInfo',
      method: 'POST',
      data: params,
      header: {
        'content-type': 'application/json' 
      }}).then(res => {
      if(res.code === "0") {
        wx.showToast({
          title: '提交成功',
          icon: 'none',
        })
       // wx.switchTab({
       //   url: '/pages/Nongchanpinpinglunlist/index',
       //  })
      } else {
        wx.showToast({
          title: res.msg,
          icon: 'none'
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

  },
  

})