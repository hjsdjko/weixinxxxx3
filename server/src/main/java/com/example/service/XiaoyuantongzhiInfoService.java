package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.XiaoyuantongzhiInfoDao;
//kuabiaojisuan1
import com.example.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.entity.XiaoyuantongzhiInfo;
//kuabiaojisuan2
import com.example.exception.CustomException;
import com.example.common.ResultCode;
import com.example.vo.XiaoyuantongzhiInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.crypto.SecureUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class XiaoyuantongzhiInfoService {

    @Resource
    private XiaoyuantongzhiInfoDao xiaoyuantongzhiInfoDao;
	
	//kuabiaojisuan3

    public XiaoyuantongzhiInfo add(XiaoyuantongzhiInfo xiaoyuantongzhiInfo) {
        //shangxchxuantupxian
        // 唯一校验
        
      // xiaoyuantongzhiInfo.setAccount((double) 0);
	  
        xiaoyuantongzhiInfoDao.insertSelective(xiaoyuantongzhiInfo);
        return xiaoyuantongzhiInfo;
    }

    public void delete(Long id) {
        xiaoyuantongzhiInfoDao.deleteByPrimaryKey(id);
    }

    public void update(XiaoyuantongzhiInfo xiaoyuantongzhiInfo) {
        //shangxchxuantupxian
		//youdianzan
        xiaoyuantongzhiInfoDao.updateByPrimaryKeySelective(xiaoyuantongzhiInfo);
    }

    public XiaoyuantongzhiInfo findById(Long id) {
        return xiaoyuantongzhiInfoDao.selectByPrimaryKey(id);
    }

    public List<XiaoyuantongzhiInfoVo> findAll() {
        return xiaoyuantongzhiInfoDao.findByTongzhibianhao("all");
    }

    public PageInfo<XiaoyuantongzhiInfoVo> findPage(String tongzhibianhao, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<XiaoyuantongzhiInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        if (user.getLevel().equals("管理员")) {all = xiaoyuantongzhiInfoDao.findByTongzhibianhao(tongzhibianhao);}

        if (user.getLevel().equals("教师")) {all = xiaoyuantongzhiInfoDao.findByTongzhibianhao(tongzhibianhao);}
        if (user.getLevel().equals("学生")) {all = xiaoyuantongzhiInfoDao.findByTongzhibianhao(tongzhibianhao);}

        return PageInfo.of(all);
    }
	
	public PageInfo<XiaoyuantongzhiInfoVo> findPageqt(String tongzhibianhao, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<XiaoyuantongzhiInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        all = xiaoyuantongzhiInfoDao.findByTongzhibianhao(tongzhibianhao);
		
	
	
        
        return PageInfo.of(all);
    }

   // public XiaoyuantongzhiInfoVo findByUserName(String name) {
//        return xiaoyuantongzhiInfoDao.findByUsername(name);
//    }

	//yoxulogindenxglu
	
    public void changeStatus(Long id) {
        XiaoyuantongzhiInfo xiaoyuantongzhiInfo = xiaoyuantongzhiInfoDao.selectByPrimaryKey(id);
        if(xiaoyuantongzhiInfo.getStatus().equals("是")){
            xiaoyuantongzhiInfo.setStatus("否");
            xiaoyuantongzhiInfoDao.updateByPrimaryKey(xiaoyuantongzhiInfo);
        }else if(xiaoyuantongzhiInfo.getStatus().equals("否")){
            xiaoyuantongzhiInfo.setStatus("是");
            xiaoyuantongzhiInfoDao.updateByPrimaryKey(xiaoyuantongzhiInfo);
        }
    }
	//ddaizdhifu
	//youtixing
	
	
   
}
