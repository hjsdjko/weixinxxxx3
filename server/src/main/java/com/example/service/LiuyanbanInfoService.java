package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.LiuyanbanInfoDao;
//kuabiaojisuan1
import com.example.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.entity.LiuyanbanInfo;
//kuabiaojisuan2
import com.example.exception.CustomException;
import com.example.common.ResultCode;
import com.example.vo.LiuyanbanInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.crypto.SecureUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class LiuyanbanInfoService {

    @Resource
    private LiuyanbanInfoDao liuyanbanInfoDao;
	
	//kuabiaojisuan3

    public LiuyanbanInfo add(LiuyanbanInfo liuyanbanInfo) {
        //shangxchxuantupxian
        // 唯一校验
        
      // liuyanbanInfo.setAccount((double) 0);
	  
        liuyanbanInfoDao.insertSelective(liuyanbanInfo);
        return liuyanbanInfo;
    }

    public void delete(Long id) {
        liuyanbanInfoDao.deleteByPrimaryKey(id);
    }

    public void update(LiuyanbanInfo liuyanbanInfo) {
        //shangxchxuantupxian
		//youdianzan
        liuyanbanInfoDao.updateByPrimaryKeySelective(liuyanbanInfo);
    }

    public LiuyanbanInfo findById(Long id) {
        return liuyanbanInfoDao.selectByPrimaryKey(id);
    }

    public List<LiuyanbanInfoVo> findAll() {
        return liuyanbanInfoDao.findByBiaoti("all");
    }

    public PageInfo<LiuyanbanInfoVo> findPage(String biaoti, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<LiuyanbanInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        if (user.getLevel().equals("管理员")) {all = liuyanbanInfoDao.findByBiaoti(biaoti);}
		//list2 if (user.getLevel().equals("cxcxx2")) {all = liuyanbanInfoDao.findByBiaotilist2(biaoti,user.getdxzhujian2()); }
		//list3 if (user.getLevel().equals("cxcxx3")) {all = liuyanbanInfoDao.findByBiaotilist3(biaoti,user.getdxzhujian3()); }
	
        
        return PageInfo.of(all);
    }
	
	public PageInfo<LiuyanbanInfoVo> findPageqt(String biaoti, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<LiuyanbanInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        all = liuyanbanInfoDao.findByBiaoti(biaoti);
		//list2 if (user.getLevel().equals("cxcxx2")) {all = liuyanbanInfoDao.findByBiaotilist2(biaoti,user.getdxzhujian2()); }
		//list3 if (user.getLevel().equals("cxcxx3")) {all = liuyanbanInfoDao.findByBiaotilist3(biaoti,user.getdxzhujian3()); }
	
        
        return PageInfo.of(all);
    }

   // public LiuyanbanInfoVo findByUserName(String name) {
//        return liuyanbanInfoDao.findByUsername(name);
//    }

	//yoxulogindenxglu
	
    public void changeStatus(Long id) {
        LiuyanbanInfo liuyanbanInfo = liuyanbanInfoDao.selectByPrimaryKey(id);
        if(liuyanbanInfo.getStatus().equals("是")){
            liuyanbanInfo.setStatus("否");
            liuyanbanInfoDao.updateByPrimaryKey(liuyanbanInfo);
        }else if(liuyanbanInfo.getStatus().equals("否")){
            liuyanbanInfo.setStatus("是");
            liuyanbanInfoDao.updateByPrimaryKey(liuyanbanInfo);
        }
    }
	//youtixing
	
	
   
}
