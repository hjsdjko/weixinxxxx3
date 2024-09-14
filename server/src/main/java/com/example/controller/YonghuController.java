package com.example.controller;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.core.util.StrUtil;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.Account;
import com.example.entity.Yonghu;
import com.example.exception.CustomException;
import com.example.service.YonghuService;
import com.example.vo.YonghuVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/yonghu")
public class YonghuController {
    @Resource
    private YonghuService yonghuService;

    @PostMapping
    public Result<Yonghu> add(@RequestBody Yonghu yonghu) {
        yonghuService.add(yonghu);
        return Result.success(yonghu);
    }
    @PostMapping("/bindUser")
    public Result bindUser(@RequestBody YonghuVo account) {
        return yonghuService.bind(account);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        yonghuService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Yonghu yonghu) {
        yonghuService.update(yonghu);
        return Result.success();
    }
    @PostMapping("/zhifu")
    public Result<Yonghu> zhifu(@RequestBody Yonghu yonghu) {
        Yonghu yonghu1 = yonghuService.findById(yonghu.getId());
        yonghu1.setMima(SecureUtil.md5(yonghu1.getMima()));
        yonghuService.update(yonghu1);
        return Result.success(yonghu1);
    }
    @GetMapping("/{id}")
    public Result<Yonghu> detail(@PathVariable Long id) {
        Yonghu yonghu = yonghuService.findById(id);
        return Result.success(yonghu);
    }


    @GetMapping("/jiebang/{id}")
    public Result<Yonghu> jiebang(@PathVariable Long id) {
        yonghuService.jiebang(id);
        return Result.success();
    }

    @GetMapping
    public Result<List<Yonghu>> all() {
        return Result.success(yonghuService.findAll());
    }

    @PostMapping("/page")
    public Result<PageInfo<Yonghu>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                         @RequestBody Yonghu yonghu) {
        return Result.success(yonghuService.findPage(pageNum, pageSize, yonghu));
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result<Yonghu> register(@RequestBody Yonghu yonghu) {
        if (StrUtil.isBlank(yonghu.getName()) || StrUtil.isBlank(yonghu.getPassword())) {
            throw new CustomException(ResultCode.PARAM_ERROR);
        }
        return Result.success(yonghuService.add(yonghu));
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result<Yonghu> login(@RequestBody Yonghu yonghu) {
        if (StrUtil.isBlank(yonghu.getName()) || StrUtil.isBlank(yonghu.getPassword())) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return Result.success(yonghuService.login(yonghu.getName(), yonghu.getPassword()));
    }

    /**
     * 重置密码为123456
     */
    @PutMapping("/resetPassword")
    public Result<Yonghu> resetPassword(@RequestParam String name) {
        return Result.success(yonghuService.resetPassword(name));
    }
    @GetMapping("/resetPassword/{id}")
    public Result<Yonghu> resetPassword(@PathVariable Long id) {
        return Result.success(yonghuService.resetPasswordById(id));
    }

    /**
     * 修改密码
     */
    @PutMapping("/changePassword")
    public Result<Boolean> changePassword(@RequestParam Long id,
                                          @RequestParam String newPassword) {
        return Result.success(yonghuService.changePassword(id, newPassword));
    }
}
