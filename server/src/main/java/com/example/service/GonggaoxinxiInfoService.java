package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.GonggaoxinxiInfoDao;
//kuabiaojisuan1
import com.example.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.entity.GonggaoxinxiInfo;
//kuabiaojisuan2
import com.example.exception.CustomException;
import com.example.common.ResultCode;
import com.example.vo.GonggaoxinxiInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.crypto.SecureUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class GonggaoxinxiInfoService {

    @Resource
    private GonggaoxinxiInfoDao gonggaoxinxiInfoDao;
	
	//kuabiaojisuan3

    public GonggaoxinxiInfo add(GonggaoxinxiInfo gonggaoxinxiInfo) {
        //shangxchxuantupxian
        // 唯一校验
        
      // gonggaoxinxiInfo.setAccount((double) 0);
	  
        gonggaoxinxiInfoDao.insertSelective(gonggaoxinxiInfo);
        return gonggaoxinxiInfo;
    }

    public void delete(Long id) {
        gonggaoxinxiInfoDao.deleteByPrimaryKey(id);
    }

    public void update(GonggaoxinxiInfo gonggaoxinxiInfo) {
        //shangxchxuantupxian
		//youdianzan
        gonggaoxinxiInfoDao.updateByPrimaryKeySelective(gonggaoxinxiInfo);
    }

    public GonggaoxinxiInfo findById(Long id) {
        return gonggaoxinxiInfoDao.selectByPrimaryKey(id);
    }

    public List<GonggaoxinxiInfoVo> findAll() {
        return gonggaoxinxiInfoDao.findByGonggaobiaoti("all");
    }

    public PageInfo<GonggaoxinxiInfoVo> findPage(String gonggaobiaoti, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<GonggaoxinxiInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        if (user.getLevel().equals("管理员")) {all = gonggaoxinxiInfoDao.findByGonggaobiaoti(gonggaobiaoti);}
        if (user.getLevel().equals("教师")) {all = gonggaoxinxiInfoDao.findByGonggaobiaoti(gonggaobiaoti);}
        if (user.getLevel().equals("学生")) {all = gonggaoxinxiInfoDao.findByGonggaobiaoti(gonggaobiaoti);}


        return PageInfo.of(all);
    }
	
	public PageInfo<GonggaoxinxiInfoVo> findPageqt(String gonggaobiaoti, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<GonggaoxinxiInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        all = gonggaoxinxiInfoDao.findByGonggaobiaoti(gonggaobiaoti);
		
	
	
        
        return PageInfo.of(all);
    }

   // public GonggaoxinxiInfoVo findByUserName(String name) {
//        return gonggaoxinxiInfoDao.findByUsername(name);
//    }

	//yoxulogindenxglu
	
    public void changeStatus(Long id) {
        GonggaoxinxiInfo gonggaoxinxiInfo = gonggaoxinxiInfoDao.selectByPrimaryKey(id);
        if(gonggaoxinxiInfo.getStatus().equals("是")){
            gonggaoxinxiInfo.setStatus("否");
            gonggaoxinxiInfoDao.updateByPrimaryKey(gonggaoxinxiInfo);
        }else if(gonggaoxinxiInfo.getStatus().equals("否")){
            gonggaoxinxiInfo.setStatus("是");
            gonggaoxinxiInfoDao.updateByPrimaryKey(gonggaoxinxiInfo);
        }
    }
	//ddaizdhifu
	//youtixing
	
	
   
}
