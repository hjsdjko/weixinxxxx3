
import {request} from "../../request/index.js";
import {config} from "../../request/config.js";
Page({

  /**
   * 页面的初始数据
   */
  data: {
    email: '',
    phones: '',
    address: '',
    avatarUrl:'',
    sex: '男',
    realname: '',
    user:{},
    array: ['男', '女'],
  },
  // 获取输入姓名
  nameInput: function (e) {
    this.setData({
      xingming: e.detail.value
    })
  },
  nameInput2: function (e) {
    this.setData({
      jiaoshixingming: e.detail.value
    })
  },
  // 获取输入手机号
  phonesInput: function (e) {

    this.setData({
      dianhua: e.detail.value
    })

  },
 // 获取输入手机号
 sfzInput: function (e) {

  this.setData({
    shenfenzheng: e.detail.value
  })

},
xuehaoInput: function (e) {

  this.setData({
    xuehao: e.detail.value
  })

},
banjiInput: function (e) {

  this.setData({
    banji: e.detail.value
  })

},
beizhuInput: function (e) {

  this.setData({
    beizhu: e.detail.value
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
    if(user.level == '教师'){
      this.getUserInfo2();
    }
    if(user.level == '学生'){
      this.getUserInfo();
    }
  },
  //获取个人信息资料
  getUserInfo:function(){
    var that = this;
    var user = wx.getStorageSync('user');
    request({
      url: '/xueshengInfo/'+user.userId,
      method: 'GET',
      header: {
        'content-type': 'application/json' 
      }
    }).then(res=>{
      if (res.code == 0) {
        that.setData({
          avatarUrl: config.baseFileUrl+JSON.parse(res.data.zhaopian),
          zhaopian: res.data.zhaopian,
          dianhua: res.data.dianhua,
          xuehao:res.data.xuehao,
          xingbie: res.data.xingbie,
          xingming: res.data.xingming,
          shenfenzheng: res.data.shenfenzheng,
          beizhu: res.data.beizhu,
        })
      } else {
        wx.showToast({
          title: res.msg,
          icon: 'none',
        })
      }
    })
  },
  getUserInfo2:function(){
    var that = this;
    var user = wx.getStorageSync('user');
    request({
      url: '/jiaoshiInfo/'+user.userId,
      method: 'GET',
      header: {
        'content-type': 'application/json' 
      }
    }).then(res=>{
      if (res.code == 0) {
        that.setData({
          avatarUrl: config.baseFileUrl+JSON.parse(res.data.zhaopian)[0],
          zhaopian: res.data.zhaopian,
          dianhua: res.data.dianhua,
          gonghao:res.data.gonghao,
          xingbie: res.data.xingbie,
          jiaoshixingming: res.data.jiaoshixingming,
          shenfenzheng: res.data.shenfenzheng,
          beizhu: res.data.beizhu,
        })
      } else {
        wx.showToast({
          title: res.msg,
          icon: 'none',
        })
      }
    })
  },
  bindPickerChange: function (e) {
    var indexs = e.detail.value;
    this.setData({
      index: indexs
    })
    if (indexs == "0") {
      this.setData({
        xingbie: '男'
      })
    } else if (indexs == 1) {
      this.setData({
        xingbie: '女'
      })
    } else {
      this.setData({
        xingbie: ''
      })
    }
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
      xingming: this.data.xingming,
      xingbie: this.data.xingbie,
      xuehao: this.data.xuehao,
      shenfenzheng:this.data.shenfenzheng,
      dianhua:this.data.dianhua,
      banji:this.data.banji,
      beizhu:this.data.beizhu,
      zhaopian:this.data.zhaopian,
    }
    request({
      url:'/xueshengInfo',
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
  logins2: function () {
    var user = wx.getStorageSync('user');
    var userId = user.userId
    var params = {
      id:userId,
      jiaoshixingming: this.data.jiaoshixingming,
      xingbie: this.data.xingbie,
      gonghao: this.data.gonghao,
      shenfenzheng:this.data.shenfenzheng,
      dianhua:this.data.dianhua,
      banji:this.data.banji,
      beizhu:this.data.beizhu,
      zhaopian:this.data.zhaopian,
    }
    request({
      url:'/jiaoshiInfo',
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