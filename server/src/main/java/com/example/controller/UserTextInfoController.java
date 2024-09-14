package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.UserTextInfo;
import com.example.service.UserTextInfoService;
import com.example.vo.UserTextInfoVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/userTextInfo")
public class UserTextInfoController {
    @Resource
    private UserTextInfoService userTextInfoService;

    @PostMapping
    public Result<UserTextInfo> add(@RequestBody UserTextInfoVo info, HttpServletRequest request) {
        List<Long> fileList = info.getFileList();
        if (!CollectionUtil.isEmpty(fileList)) {
            info.setFileIds(fileList.toString());
        }
        info.setClick(0);
        userTextInfoService.add(info);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        userTextInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody UserTextInfoVo info) {
        List<Long> fileList = info.getFileList();
        if (!CollectionUtil.isEmpty(fileList)) {
            info.setFileIds(fileList.toString());
        }
        userTextInfoService.update(info);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<UserTextInfo> detail(@PathVariable Long id) {
        UserTextInfo info = userTextInfoService.findById(id);
        return Result.success(info);
    }
    @GetMapping("/fenxiang/{id}")
    public Result<UserTextInfo> fenxiang(@PathVariable Long id) {
        userTextInfoService.fenxiang(id);
        return Result.success();
    }
    @GetMapping("/allfenxiang/getAll")
    public Result<List<UserTextInfo>> allfenxiang() {
        List<UserTextInfo> info = userTextInfoService.findFenxiang();
        return Result.success(info);
    }

    @GetMapping("/quxiaofenxiang/{id}")
    public Result<UserTextInfo> quxiaofenxiang(@PathVariable Long id) {
        userTextInfoService.quxiaofenxiang(id);
        return Result.success();
    }
    @GetMapping("/click/{id}")
    public Result<UserTextInfo> click(@PathVariable Long id) {
        UserTextInfo info = userTextInfoService.click(id);
        return Result.success(info);
    }
    @GetMapping("/findUserPaper/{id}/{level}")
    public Result<List<UserTextInfo>> userOtherPaper(@PathVariable Long id, @PathVariable String level) {
        List<UserTextInfo> info = userTextInfoService.findPaperByUser(id,level);
        return Result.success(info);
    }
    @GetMapping("/findUserPaper2/{id}/{level}")
    public Result<List<UserTextInfo>> userOtherPaper2(@PathVariable Long id, @PathVariable String level) {
        List<UserTextInfo> info = userTextInfoService.findPaperByUser2(id,level);
        return Result.success(info);
    }
    @GetMapping("/findByXuqiuId/{xuqiuId}")
    public Result<List<UserTextInfo>> userOtherPaper(@PathVariable Long xuqiuId) {
        List<UserTextInfo> info = userTextInfoService.findByXuqiuId(xuqiuId);
        return Result.success(info);
    }

    @GetMapping
    public Result<List<UserTextInfoVo>> all() {
        return Result.success(userTextInfoService.findAll());
    }

    @GetMapping("/page")
    public Result<PageInfo<UserTextInfoVo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 HttpServletRequest request) {
        return Result.success(userTextInfoService.findPage(pageNum, pageSize, request));
    }
    @GetMapping("/page/{name}")
    public Result<PageInfo<UserTextInfoVo>> page(@PathVariable String name,
                                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                                 @RequestParam(defaultValue = "5") Integer pageSize,
                                                 HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        if(account != null && account.getLevel() == "超级管理员") {
            return Result.success(userTextInfoService.findPage(name, pageNum, pageSize, request));
        }else {
            return Result.success(userTextInfoService.findPageByUserId(name,pageNum, pageSize, request));
        }
    }
//    @GetMapping("/pageByUserId/{name}")
//    public Result<PageInfo<UserTextInfoVo>> pageByUserId(@PathVariable String name,
//                                                         @RequestParam(defaultValue = "1") Integer pageNum,
//                                                         @RequestParam(defaultValue = "5") Integer pageSize,
//                                                         Long userId,
//                                                         HttpServletRequest request) {
//        return Result.success(userTextInfoService.findPageByUserId(name, pageNum, pageSize, request));
//    }
    @GetMapping("/my")
    public Result<PageInfo<UserTextInfoVo>> mypage(
                                                         @RequestParam(defaultValue = "1") Integer pageNum,
                                                         @RequestParam(defaultValue = "5") Integer pageSize,
                                                         HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        return Result.success(userTextInfoService.findPageByMy(pageNum, pageSize, account));
    }
}
