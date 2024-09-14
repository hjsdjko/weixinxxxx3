package com.example.service;

import com.example.dao.UserTextInfoDao;
import com.example.entity.Account;
import com.example.entity.PaperCommentInfo;
import com.example.entity.UserTextInfo;
import com.example.vo.UserTextInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserTextInfoService {

    @Resource
    private UserTextInfoDao userTextInfoDao;
    @Resource
    private PaperCommentInfoService paperCommentInfoService;

    public UserTextInfo add(UserTextInfo info) {
        userTextInfoDao.insertSelective(info);
        return info;
    }

    public void delete(Long id) {
        userTextInfoDao.deleteByPrimaryKey(id);
    }

    public void update(UserTextInfo info) {
        userTextInfoDao.updateByPrimaryKeySelective(info);
    }

    public UserTextInfo findById(Long id) {
        return userTextInfoDao.selectByPrimaryKey(id);
    }

    public List<UserTextInfoVo> findAll() {

        List<UserTextInfoVo> all = userTextInfoDao.findByName("all");
        for (UserTextInfo userTextInfo : all) {
            getRelInfo(userTextInfo);
        }
        return all;
    }

    public PageInfo<UserTextInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserTextInfoVo> all = findAllPage(request, name);
        for (UserTextInfo userTextInfo : all) {
            getRelInfo(userTextInfo);
        }
        return PageInfo.of(all);
    }
    public PageInfo<UserTextInfoVo> findPage(Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserTextInfoVo> all = userTextInfoDao.findAll();
        for (UserTextInfo userTextInfo : all) {
            getRelInfo(userTextInfo);
        }
        return PageInfo.of(all);
    }

    public List<UserTextInfoVo> findAllPage(HttpServletRequest request, String name) {
		return userTextInfoDao.findByName(name);
    }

    public PageInfo<UserTextInfoVo> findPageByUserId(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserTextInfoVo> all = userTextInfoDao.findAllPageByUserId(request, name);
        for (UserTextInfo userTextInfo : all) {
            getRelInfo(userTextInfo);
        }
        return PageInfo.of(all);
    }

    public PageInfo<UserTextInfoVo> findPageByMy(Integer pageNum, Integer pageSize, Account account) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserTextInfoVo> all = userTextInfoDao.findAllPageByMy(account);
        for (UserTextInfo userTextInfo : all) {
            getRelInfo(userTextInfo);
        }
        return PageInfo.of(all);
    }


    public List<UserTextInfo> findPaperByUser(Long id, String level) {
        List<UserTextInfo> all = userTextInfoDao.findAllPageByUser(id,level);
        for (UserTextInfo userTextInfo : all) {
            getRelInfo(userTextInfo);
        }
        return all;
    }
    public List<UserTextInfo> findPaperByUser2(Long id, String level) {
        List<UserTextInfo> all = userTextInfoDao.findAllPageByUser2(id,level);
        for (UserTextInfo userTextInfo : all) {
            getRelInfo(userTextInfo);
        }
        return all;
    }

    public UserTextInfo click(Long id) {
        UserTextInfo userTextInfo = userTextInfoDao.selectByPrimaryKey(id);
        userTextInfo.setClick(userTextInfo.getClick()+1);
        userTextInfoDao.updateByPrimaryKey(userTextInfo);
        return userTextInfo;
    }

    private void getRelInfo(UserTextInfo userTextInfo){
        if(userTextInfo!=null){
            List<PaperCommentInfo> paperCommentInfoList = paperCommentInfoService.findAll(userTextInfo.getId());
            userTextInfo.setCommentCount(paperCommentInfoList.size());
        }
    }

    public List<UserTextInfo> findByXuqiuId(Long xuqiuId) {
        List<UserTextInfo> all = userTextInfoDao.findByXuqiuId(xuqiuId);
        for (UserTextInfo userTextInfo : all) {
            getRelInfo(userTextInfo);
        }
        return all;
    }

    public UserTextInfo findByIdDetail(Long foreignId) {
        UserTextInfo userTextInfo = userTextInfoDao.findByIdDetail(foreignId);
        getRelInfo(userTextInfo);
        return userTextInfo;
    }

    public void fenxiang(Long id) {
        UserTextInfo userTextInfo = userTextInfoDao.selectByPrimaryKey(id);
        userTextInfo.setFenxiang("是");
        userTextInfoDao.updateByPrimaryKey(userTextInfo);
    }

    public void quxiaofenxiang(Long id) {
        UserTextInfo userTextInfo = userTextInfoDao.selectByPrimaryKey(id);
        userTextInfo.setFenxiang("否");
        userTextInfoDao.updateByPrimaryKey(userTextInfo);
    }

    public List<UserTextInfo> findFenxiang() {
        List<UserTextInfo> userTextInfos =  userTextInfoDao.findFenxiang();
        for (UserTextInfo userTextInfo : userTextInfos) {
            getRelInfo(userTextInfo);
        }
        return userTextInfos;
    }
}
