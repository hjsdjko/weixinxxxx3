import {request} from "../../request/index.js";
import {config} from '../../request/config';

Page({
    data: {
        defaultImageUrl: '../../imgs/default.png',
        formats: {},
        bottom: 0,
        readOnly: false,
        placeholder: '介绍一下你的详情吧，支持文字和图片...',
        _focus: false,
        faceUrl:'',
        fileList:[],
        //例如 xxxList: [],
		goodsInfoCarouselList: [],
		goodsInfoRecommendList: [],
		goodsInfoHotList: [],
		goodsInfoGoodsList: [],
		userTextInfoList: [],
    },
    onLoad: function () {
        //例如 this.getXxxList();
		this.getGoodsInfoCarouselList();
		this.getGoodsInfoRecommendList();
		this.getGoodsInfoHotList();
		this.getGoodsInfoGoodsList();
    },
    onShow(e) {
        let user = wx.getStorageSync("user");
        let url = '/pages/login/index?isTabBar=1&url=/pages/fabu/index';
        if (!user) {
          wx.navigateTo({
            url: url,
          })
          return;
        }
    },
readOnlyChange() {
    this.setData({
      readOnly: !this.data.readOnly
    })
  },
  onLoad:function (options) {
    
    const id = options.id;
    this.setData({
      xuqiuId: id
    })
  },
  // 编辑器初始化完成时触发
  onEditorReady() {
    const that = this;
    wx.createSelectorQuery().select('#editor').context(function(res) {
      that.editorCtx = res.context;
    }).exec();
  },
  undo() {
    this.editorCtx.undo();
  },
  redo() {
    this.editorCtx.redo();
  },
  format(e) {
    let {
      name,
      value
    } = e.target.dataset;
    if (!name) return;
    // console.log('format', name, value)
    this.editorCtx.format(name, value);
  },
  // 通过 Context 方法改变编辑器内样式时触发，返回选区已设置的样式
  onStatusChange(e) {
    const formats = e.detail;
    this.setData({
      formats
    });
  },
  // 插入分割线
  insertDivider() {
    this.editorCtx.insertDivider({
      success: function() {
        console.log('insert divider success')
      }
    });
  },
  // 清除
  clear() {
    this.editorCtx.clear({
      success: function(res) {
        console.log("clear success")
      }
    });
  },
  // 移除样式
  removeFormat() {
    this.editorCtx.removeFormat();
  },
  // 插入当前日期
  insertDate() {
    const date = new Date()
    const formatDate = `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()}`
    this.editorCtx.insertText({
      text: formatDate
    });
  },
  // 插入图片
  insertImage() {
    var me = this;
    wx.chooseImage({
      count: 1,
      success: function (res) {
        var tempFilePaths = res.tempFilePaths;
        console.log(tempFilePaths);
        wx.showLoading({
          title: '上传中...',
        })
        wx.uploadFile({
            url:'http://localhost:8888/files/upload',
            filePath: tempFilePaths[0],
            name: 'file',
            header: {
              'content-type': 'application/json', // 默认值
            },
            success: function (res) {
              var data = JSON.parse(res.data);
              wx.hideLoading();
              if (data.code == "0") {
                wx.showToast({
                  title: '上传成功!~~',
                  icon: "success"
                });
                var imageUrl = 'http://localhost:8888/files/download/' + data.data.id;
                me.editorCtx.insertImage({
                    src:imageUrl,
                    width:'100%',
                    data: {
                      id: 'abcd',
                      role: 'god'
                    },
                    success: () => {
                      console.log('insert image success')
                    }
                  })
              } else{
                wx.showToast({
                  title: data.msg
                });
              } 
            }
          })
      }
    });
  },
  //选择图片
  chooseImage(e) {
    wx.chooseImage({
      sizeType: ['original', 'compressed'], //可选择原图或压缩后的图片
      sourceType: ['album', 'camera'], //可选择性开放访问相册、相机
      success: res => {
        const images = this.data.images.concat(res.tempFilePaths);
        this.data.images = images.length <= 3 ? images : images.slice(0, 3);
      }
    })
  },
  changeFace: function () {
    var me = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['compressed'],
      sourceType: ['album'],
      success: function (res) {
        var tempFilePaths = res.tempFilePaths;
        console.log(tempFilePaths);
        wx.showLoading({
          title: '上传中...',
        })
        wx.uploadFile({
            url:'http://localhost:8888/files/upload',
            filePath: tempFilePaths[0],
            name: 'file',
            header: {
              'content-type': 'application/json', // 默认值
            },
            success: function (res) {
              var data = JSON.parse(res.data);
              const fileList = [];
              fileList.push(data.data.id)
    
              me.setData({
                fileList: fileList
              });
              wx.hideLoading();
              if (data.code == "0") {
                wx.showToast({
                  title: '上传成功!~~',
                  icon: "success"
                });
                var imageUrl = 'http://localhost:8888/files/download/' + data.data.id;
                me.setData({
                  faceUrl: imageUrl
                });
              } else{
                wx.showToast({
                  title: data.msg
                });
              } 
            }
          })
      }
    })
  },
  doLogin(e){
    let user = wx.getStorageSync("user");
    var formObject = e.detail.value;
    var title = formObject.title;
    var description = formObject.description;
    this.editorCtx.getContents({
        success: (res) => {
          console.log(res.html)
          var html = res.html
          console.log(title,description,html,user.userId,user.level,this.data.fileList)
          let data = {title,description,content:html,userId:user.userId,level:user.level,fileList:this.data.fileList,xuqiuId:this.data.xuqiuId};
      request({url: '/userTextInfo', method: 'POST', data:data}).then(res => {
        if (res.code === '0') {
          wx.showToast({
            title:"发布成功"
          })
          wx.switchTab({
            url: '/pages/index/index'
          });
        } else {
          wx.showToast({
            title: res.msg,
            icon: 'error'
          })
        }
      })
        },
        fail: (res) => {
          console.log("fail：" , res);
        }
      });
  }
});
