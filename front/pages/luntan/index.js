import {request} from "../../request/index.js";
import {config} from '../../request/config';

Page({
    data: {
        defaultImageUrl: '../../imgs/default.png',
        //例如 xxxList: [],
        userTextInfoList:[],
        userTextInfoList2:[],
    },
    onLoad: function () {
    this.getUserTextInfoList2();
    },
    onShow:function(){
      const user = wx.getStorageSync('user');
    if(user.userId){
      this.getUserTextInfoList2();
    }else{
      wx.switchTab({
        url: '/pages/user/index'
      });
    }
      },
      goFabu(){
        wx.navigateTo({
          url: '/pages/fabu/index'
        })
      },
  getUserTextInfoList2() {
    request({url: '/userTextInfo/allfenxiang/getAll'}).then(res => {
        if(res.code === '0') {
            let userTextInfoList2 = res.data;
            userTextInfoList2.forEach(item => {
                    if(!item.fileIds || item.fileIds === '[]') { 
                    } else {
                        let fileArr = JSON.parse(item.fileIds)
                        item.url = config.baseFileUrl + fileArr[0];
                    }
                    if(!item.avater || item.avater === '[]') {
                  } else {
                      let fileArr = JSON.parse(item.avater)
                      item.avater = config.baseFileUrl + fileArr[0];
                  }
                });
                this.setData({
                  userTextInfoList2
               })
            }
    })
  },
});
