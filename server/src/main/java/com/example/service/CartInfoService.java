package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.example.dao.CartInfoDao;
import com.example.entity.ShangpinxinxiInfo;
import com.example.entity.CartInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartInfoService {

    @Resource
    private CartInfoDao cartInfoDao;
    @Resource
    private ShangpinxinxiInfoService shangpinxinxiInfoService;

    public List<ShangpinxinxiInfo> findAll(Long userId) {
        List<ShangpinxinxiInfo> goodsList = new ArrayList<>();
        List<CartInfo> cartInfoList = cartInfoDao.findCartByUserId(userId);
        for (CartInfo cartInfo : cartInfoList) {
            Long goodsId = cartInfo.getShangpinxinxiId();
            ShangpinxinxiInfo goods = shangpinxinxiInfoService.findById(goodsId);
            if (goods != null) {
                // 注意这里返回的count是用户加入农产品的数量，而不是农产品的库存
                goods.setCount(cartInfo.getCount());
                // 这里返回的id也是购物车里农产品的关系id
                goods.setId(cartInfo.getShangpinxinxiId());
                goodsList.add(goods);
            }
        }
        return goodsList;
    }
    public CartInfo findById(Long id) {
        return cartInfoDao.selectByPrimaryKey(id);
    }

    public CartInfo add(CartInfo detailInfo) {
        Long userId = detailInfo.getUserId();
        Long goodsId = detailInfo.getShangpinxinxiId();
        // 先查询购物车里有没有该农产品，有就更新数量，没有就添加
        Example example = new Example(CartInfo.class);
        example.createCriteria()
                .andEqualTo("userId", userId)
                .andEqualTo("shangpinxinxiId", goodsId);
        List<CartInfo> infos = cartInfoDao.selectByExample(example);
        if (CollectionUtil.isEmpty(infos)) {
            // 新增
            detailInfo.setCreateTime(DateUtil.formatDateTime(new Date()));
            cartInfoDao.insertSelective(detailInfo);
        } else {
            // 更新
            CartInfo cartInfo = infos.get(0);
            cartInfo.setCount(cartInfo.getCount() + detailInfo.getCount());
            cartInfoDao.updateByPrimaryKeySelective(cartInfo);
        }
        return detailInfo;
    }

    public CartInfo update(CartInfo detailInfo) {
        cartInfoDao.updateByPrimaryKeySelective(detailInfo);
        return detailInfo;
    }

    public void delete(Long id) {
        cartInfoDao.deleteByPrimaryKey(id);
    }

    public void empty(Long userId) {
        cartInfoDao.deleteByUserId(userId);
    }

    public void deleteGoods(Long userId, Long goodsId) {
        cartInfoDao.deleteGoods(userId, goodsId);
    }
}
