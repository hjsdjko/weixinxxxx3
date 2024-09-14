import {request} from "../../request/index.js";
import {config} from '../../request/config';
const util = require('../../utils/util.js')
Page({
    data: {
      tongzhiList:[],
      gonggaoList:[]
    },
    onLoad: function () {

    },
    onShow:function(){
      this.getUserTextInfoList();
      this.getAdminTextInfoList();
      },
      getAdminTextInfoList() {
        request({url: '/gonggaoxinxiInfo'}).then(res => {
            if(res.code === '0') {
                let gonggaoList = res.data;
                this.setData({
                  gonggaoList,
                })
            } else {
                wx.showToast({
                    title: res.msg,
                    icon: 'none'
                })
            }
        })
      },
      getUserTextInfoList() {
        request({url: '/xiaoyuantongzhiInfo'}).then(res => {
            if(res.code === '0') {
                let tongzhiList = res.data;
                this.setData({
                  tongzhiList,
                })
            } else {
                wx.showToast({
                    title: res.msg,
                    icon: 'none'
                })
            }
        })
      },
      navigateToTiwen(e) {
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
          wx.switchTab({
            url: '/pages/tiwen/index'
          });
        }
      },
      navigateToChuru(e){
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
          wx.switchTab({
            url: '/pages/churu/index'
          });
        }
      },
});
