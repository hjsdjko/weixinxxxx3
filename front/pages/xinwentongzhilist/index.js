import {request} from "../../request/index.js";
import {config} from '../../request/config';
const util = require('../../utils/util.js')
Page({
    data: {
      //tongzhiList:[],
      xinwentongzhiList:[]
    },
    onLoad: function () {

    },
    onShow:function(){
      //this.getUserTextInfoList();
      this.getAdminTextInfoList();
      },
      getAdminTextInfoList() {
        request({url: '/xinwentongzhiInfo'}).then(res => {
            if(res.code === '0') {
                let xinwentongzhiList = res.data;
                this.setData({
                  xinwentongzhiList,
                })
            } else {
                wx.showToast({
                    title: res.msg,
                    icon: 'none'
                })
            }
        })
      },

});
