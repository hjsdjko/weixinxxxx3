import {request} from "../../request/index.js";
import {config} from '../../request/config';

Page({

    /**
     * 页面的初始数据
     */
    data: {
        goodsInfoGoodsList: [],//菜品列表
    },


    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var url='/shangpinxinxiInfo';
        request({url: url}).then(res => {
            if(res.code === '0') {
                let goodsInfoGoodsList = res.data;
                goodsInfoGoodsList.forEach(item => {
                  if(!item.tupian || item.tupian === '[]') {
                    item.url = this.data.defaultImageUrl;
                } else {
                    let fileArr = JSON.parse(item.tupian)
                    item.url = config.baseFileUrl + fileArr[0];
                }
                });
                this.setData({
                    goodsInfoGoodsList
                })
            } else {
                wx.showToast({
                    title: res.msg,
                    icon: 'none'
                })
            }
        })
    },
    //搜索
    search:function(e){
        var name=e.detail.value;
        this.getGoodsInfoGoodsList(name);
    },

    /* 获取本站菜品开始 */
getGoodsInfoGoodsList(name){
  var url='/shangpinxinxiInfo/searchGoods?text='+name;
        request({url: url}).then(res => {
            if(res.code === '0') {
                let goodsInfoGoodsList = res.data;
                goodsInfoGoodsList.forEach(item => {
                  if(!item.tupian || item.tupian === '[]') {
                    item.url = this.data.defaultImageUrl;
                } else {
                    let fileArr = JSON.parse(item.tupian)
                    item.url = config.baseFileUrl + fileArr[0];
                }
                });
                this.setData({
                    goodsInfoGoodsList
                })
            } else {
                wx.showToast({
                    title: res.msg,
                    icon: 'none'
                })
            }
        })
},
/* 获取本站菜品结束 */

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