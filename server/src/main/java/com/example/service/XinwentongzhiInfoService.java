package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.XinwentongzhiInfoDao;
//kuabiaojisuan1
import com.example.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.entity.XinwentongzhiInfo;
//kuabiaojisuan2
import com.example.exception.CustomException;
import com.example.common.ResultCode;
import com.example.vo.XinwentongzhiInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.crypto.SecureUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class XinwentongzhiInfoService {

    @Resource
    private XinwentongzhiInfoDao xinwentongzhiInfoDao;
	
	//kuabiaojisuan3

    public XinwentongzhiInfo add(XinwentongzhiInfo xinwentongzhiInfo) {
        List<Long> shouyetupianflst = xinwentongzhiInfo.getShouyetupianflst();
        if (!CollectionUtil.isEmpty(shouyetupianflst)) {
            xinwentongzhiInfo.setShouyetupian(shouyetupianflst.toString());
        }
		
		        //shangxchxuantupxian
        // 唯一校验
        
      // xinwentongzhiInfo.setAccount((double) 0);
	  
        xinwentongzhiInfoDao.insertSelective(xinwentongzhiInfo);
        return xinwentongzhiInfo;
    }

    public void delete(Long id) {
        xinwentongzhiInfoDao.deleteByPrimaryKey(id);
    }

    public void update(XinwentongzhiInfo xinwentongzhiInfo) {
        List<Long> shouyetupianflst = xinwentongzhiInfo.getShouyetupianflst();
        if (!CollectionUtil.isEmpty(shouyetupianflst)) {
            xinwentongzhiInfo.setShouyetupian(shouyetupianflst.toString());
        }
		
		        //shangxchxuantupxian
		 if (xinwentongzhiInfo.getDianzan_d() != null) {
            XinwentongzhiInfo xinwentongzhi = findById(xinwentongzhiInfo.getId());
            xinwentongzhiInfo.setDianzan_d(String.valueOf(Integer.valueOf(xinwentongzhi.getDianzan_d()) + Integer.valueOf(xinwentongzhiInfo.getDianzan_d())));
        }
        if (xinwentongzhiInfo.getDianzan_c() != null) {
            XinwentongzhiInfo xinwentongzhi = findById(xinwentongzhiInfo.getId());
            xinwentongzhiInfo.setDianzan_c(String.valueOf(Integer.valueOf(xinwentongzhi.getDianzan_c()) + Integer.valueOf(xinwentongzhiInfo.getDianzan_c())));
        }
		if (xinwentongzhiInfo.getDianjilv() != null) {
            XinwentongzhiInfo xinwentongzhi = findById(xinwentongzhiInfo.getId());
            xinwentongzhiInfo.setDianjilv(String.valueOf(Integer.valueOf(xinwentongzhi.getDianjilv()) + 1));
        }
        xinwentongzhiInfoDao.updateByPrimaryKeySelective(xinwentongzhiInfo);
    }

    public XinwentongzhiInfo findById(Long id) {
        return xinwentongzhiInfoDao.selectByPrimaryKey(id);
    }

    public List<XinwentongzhiInfoVo> findAll() {
        return xinwentongzhiInfoDao.findByBiaoti("all");
    }

    public PageInfo<XinwentongzhiInfoVo> findPage(String biaoti, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<XinwentongzhiInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        if (user.getLevel().equals("管理员")) {all = xinwentongzhiInfoDao.findByBiaoti(biaoti);}
		//list2 if (user.getLevel().equals("cxcxx2")) {all = xinwentongzhiInfoDao.findByBiaotilist2(biaoti,user.getdxzhujian2()); }
		//list3 if (user.getLevel().equals("cxcxx3")) {all = xinwentongzhiInfoDao.findByBiaotilist3(biaoti,user.getdxzhujian3()); }
	
        
        return PageInfo.of(all);
    }
	
	public PageInfo<XinwentongzhiInfoVo> findPageqt(String biaoti,String leibie, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<XinwentongzhiInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        all = xinwentongzhiInfoDao.findByBiaotilb(biaoti,leibie);
		//list2 if (user.getLevel().equals("cxcxx2")) {all = xinwentongzhiInfoDao.findByBiaotilist2(biaoti,user.getdxzhujian2()); }
		//list3 if (user.getLevel().equals("cxcxx3")) {all = xinwentongzhiInfoDao.findByBiaotilist3(biaoti,user.getdxzhujian3()); }
	
        
        return PageInfo.of(all);
    }

   // public XinwentongzhiInfoVo findByUserName(String name) {
//        return xinwentongzhiInfoDao.findByUsername(name);
//    }

	//yoxulogindenxglu
	
    public void changeStatus(Long id) {
        XinwentongzhiInfo xinwentongzhiInfo = xinwentongzhiInfoDao.selectByPrimaryKey(id);
        if(xinwentongzhiInfo.getStatus().equals("是")){
            xinwentongzhiInfo.setStatus("否");
            xinwentongzhiInfoDao.updateByPrimaryKey(xinwentongzhiInfo);
        }else if(xinwentongzhiInfo.getStatus().equals("否")){
            xinwentongzhiInfo.setStatus("是");
            xinwentongzhiInfoDao.updateByPrimaryKey(xinwentongzhiInfo);
        }
    }
	//youtixing
	
	
   
}
