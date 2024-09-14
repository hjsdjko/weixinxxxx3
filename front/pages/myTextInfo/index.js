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
  onShow:function(){
    let user = wx.getStorageSync('user');
    this.getUserTextInfoList(user.userId,user.level);
  },
  getUserTextInfoList(id,level) {
    request({url: '/userTextInfo/findUserPaper/'+id+'/'+level}).then(res => {
        if(res.code === '0') {
            let userTextInfoList = res.data;
            userTextInfoList.forEach(item => {
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
                  userTextInfoList
               })
            }
    })
  },
})