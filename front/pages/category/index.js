import {request} from "../../request/index.js";
import {config} from '../../request/config';
Page({
    data: {
        defaultImageUrl: '../../imgs/default.png',
        // 左侧的菜单数据
        leftMenuList: [],
        leftMenuList2: [],
        show1:true,
        show2:false,
        // 右侧的菜品数据
        rightContent: [],
        rightContent2: [],
        goodsInfoList: [],
        // 被点击的左侧的菜单
        currentIndex: 1,
        currentIndex2: 1,
        // 右侧内容的滚动条距离顶部的距离
        scrollTop: 0
    },
    // 接口的返回数据
    Cates: [],

    onLoad: function (options) {
        this.getCates();
        this.getCates2();
    },
    show1(){
      this.setData({
        show1:true,
        show2:false,
      })
    },
    show2(){
      this.setData({
        show1:false,
        show2:true,
      })
    },
    // 获取分类数据
    getCates() {
        request({url: '/shangpinleibieInfo'}).then(res => {
            if (res.code === '0') {
              let info = res.data;
                this.setData({
                    leftMenuList: info
                })
                this.getGoodsList(info[0].leibie)
            }
        })
    },
    
    getGoodsList(type) {
      
        request({url: '/shangpinxinxiInfo/findByType/' + type}).then(res => {
          if (res.code === '0') {
            let shangpinxinxiList = res.data;
            shangpinxinxiList.forEach(item => {
              if(!item.tupian || item.tupian === '[]') {
                  item.url = this.data.defaultImageUrl;
              } else {
                  let fileArr = JSON.parse(item.tupian)
                  item.url = config.baseFileUrl + fileArr[0];
              }
              this.setData({
                  rightContent: shangpinxinxiList
              })
      })
    }
        })
    },
  bindfocus:function(){
      wx.navigateTo({
        url: '/pages/search/search',
      })
  },
    // 左侧菜单的点击事件
    handleItemTap(e) {
        /*
        1 获取被点击的标题身上的索引
        2 给data中的currentIndex赋值就可以了
        3 根据不同的索引来渲染右侧的菜品内容
         */
        this.setData({
          rightContent: []
      })
        const {id,type} = e.currentTarget.dataset;
        let rightContent = this.getGoodsList(type);
        this.setData({
            currentIndex: id,
            fenlei: type,
            rightContent: rightContent,
            // 重新设置 右侧内容的scroll-view标签的距离顶部的距离
            scrollTop: 0
        })

    },
   
})