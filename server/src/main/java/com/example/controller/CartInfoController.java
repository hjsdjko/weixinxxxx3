package com.example.controller;

import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.ShangpinxinxiInfo;
import com.example.entity.CartInfo;
import com.example.exception.CustomException;
import com.example.service.CartInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/cartInfo")
public class CartInfoController {
    @Resource
    private CartInfoService cartInfoService;

    /**
     * 查询所有购物车（不分页）
     *
     * @return 购物车list
     */
    @GetMapping
    public Result<List<ShangpinxinxiInfo>> findAll(@RequestParam Long userId) {
        return Result.success(cartInfoService.findAll(userId));
    }


    /**
     * 根据id获取购物车
     *
     * @param id 购物车id
     * @return 购物车信息
     */
    @GetMapping("/{id}")
    public Result<CartInfo> findById(@PathVariable Long id) {
        return Result.success(cartInfoService.findById(id));
    }

    /**
     * 添加购物车
     *
     * @param detailInfo 购物车信息
     * @return 购物车信息
     */
    @PostMapping
    public Result<CartInfo> add(@RequestBody CartInfo detailInfo) {
        return Result.success(cartInfoService.add(detailInfo));
    }

    /**
     * 更新购物车详情
     *
     * @param detailInfo 农产品购物车信息
     * @return 农产品购物车信息
     */
    @PutMapping
    public Result<CartInfo> update(@RequestBody CartInfo detailInfo) {
        if (detailInfo.getId() == null) {
            throw new CustomException(ResultCode.PARAM_ERROR);
        }
        return Result.success(cartInfoService.update(detailInfo));
    }

    /**
     * 删除购物车
     *
     * @param id 农产品id
     * @return result
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        cartInfoService.delete(id);
        return Result.success();
    }

    /**
     * 删除购物车
     *
     * @param userId 用户
     * @param goodsId 农产品id
     * @return result
     */
    @DeleteMapping("/goods/{userId}/{goodsId}")
    public Result deleteGoods(@PathVariable Long userId, @PathVariable Long goodsId) {
        cartInfoService.deleteGoods(userId, goodsId);
        return Result.success();
    }

    /**
     * 删除购物车
     *
     * @param userId 用户id
     * @return result
     */
    @DeleteMapping("/empty/{userId}")
    public Result empty(@PathVariable Long userId) {
        cartInfoService.empty(userId);
        return Result.success();
    }
}
