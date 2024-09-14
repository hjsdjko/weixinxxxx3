package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.AdminInfoDao;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.entity.AdminInfo;
import com.example.exception.CustomException;
import com.example.common.ResultCode;
import com.example.vo.AdminInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.crypto.SecureUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class AdminInfoService {

    @Resource
    private AdminInfoDao adminInfoDao;

    public AdminInfo add(AdminInfo adminInfo) {
        List<Long> fileList = adminInfo.getFileList();
        if (!CollectionUtil.isEmpty(fileList)) {
            adminInfo.setFileIds(fileList.toString());
        }
        // 唯一校验
        int count = adminInfoDao.checkRepeat("username", adminInfo.getUsername(), null);
        if (count > 0) {
            throw new CustomException("1001", "用户名\"" + adminInfo.getUsername() + "\"已存在");
        }
        if (StringUtils.isEmpty(adminInfo.getMima())) {
            // 默认密码123456
            adminInfo.setMima(SecureUtil.md5("123456"));
        } else {
            adminInfo.setMima(SecureUtil.md5(adminInfo.getMima()));
        }
        adminInfoDao.insertSelective(adminInfo);
        return adminInfo;
    }

    public void delete(Long id) {
        adminInfoDao.deleteByPrimaryKey(id);
    }

    public void update(AdminInfo adminInfo) {
        adminInfoDao.updateByPrimaryKeySelective(adminInfo);
    }

    public AdminInfo findById(Long id) {
        return adminInfoDao.selectByPrimaryKey(id);
    }

    public List<AdminInfoVo> findAll() {
        return adminInfoDao.findByNickName("all");
    }

    public PageInfo<AdminInfoVo> findPage(String nickName, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<AdminInfoVo> all = adminInfoDao.findByNickName(nickName);
        return PageInfo.of(all);
    }

    public AdminInfoVo findByUserName(String username) {
        return adminInfoDao.findByUsername(username);
    }

    public AdminInfo login(String username, String mima) {
        AdminInfo adminInfo = adminInfoDao.findByUsername(username);
        if (adminInfo == null) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        if (!SecureUtil.md5(mima).equalsIgnoreCase(adminInfo.getMima())) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return adminInfo;
    }

    public void update2(AdminInfoVo adminInfo) {
        List<Long> fileList = adminInfo.getFileList();
        if (!CollectionUtil.isEmpty(fileList)) {
            adminInfo.setFileIds(fileList.toString());
        }
        if (StringUtils.isEmpty(adminInfo.getMima())) {
            // 默认密码123456
            adminInfo.setMima(SecureUtil.md5("123456"));
        } else {
            adminInfo.setMima(SecureUtil.md5(adminInfo.getMima()));
        }
        adminInfoDao.updateByPrimaryKeySelective(adminInfo);
    }
}
