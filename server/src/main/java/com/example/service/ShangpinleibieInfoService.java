package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.ShangpinleibieInfoDao;
//kuabiaojisuan1
import com.example.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.entity.ShangpinleibieInfo;
//kuabiaojisuan2
import com.example.exception.CustomException;
import com.example.common.ResultCode;
import com.example.vo.ShangpinleibieInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.crypto.SecureUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ShangpinleibieInfoService {

    @Resource
    private ShangpinleibieInfoDao shangpinleibieInfoDao;
	
	
	//kuabiaojisuan3

    public ShangpinleibieInfo add(ShangpinleibieInfo shangpinleibieInfo) {
        //shangxchxuantupxian
        // 唯一校验
        
      // shangpinleibieInfo.setAccount((double) 0);
	  
        shangpinleibieInfoDao.insertSelective(shangpinleibieInfo);
        return shangpinleibieInfo;
    }
	
	public List<Map<String,String>> findByDiqu() {
        return shangpinleibieInfoDao.findByDiqu();
    }

    public void delete(Long id) {
        shangpinleibieInfoDao.deleteByPrimaryKey(id);
    }

    public void update(ShangpinleibieInfo shangpinleibieInfo) {
        //shangxchxuantupxian
		//youdianzan
        shangpinleibieInfoDao.updateByPrimaryKeySelective(shangpinleibieInfo);
    }

    public ShangpinleibieInfo findById(Long id) {
        return shangpinleibieInfoDao.selectByPrimaryKey(id);
    }
	
    public List<ShangpinleibieInfoVo> findAll() {
        return shangpinleibieInfoDao.findByLeibie("all");
    }

    public PageInfo<ShangpinleibieInfoVo> findPage(String leibie, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<ShangpinleibieInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        if (user.getLevel().equals("管理员")) {all = shangpinleibieInfoDao.findByLeibie(leibie);}
		
        
        return PageInfo.of(all);
    }
	
	public PageInfo<ShangpinleibieInfoVo> findPageqt(String leibie, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<ShangpinleibieInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        all = shangpinleibieInfoDao.findByLeibie(leibie);
		
	
	
        
        return PageInfo.of(all);
    }

   // public ShangpinleibieInfoVo findByUserName(String name) {
//        return shangpinleibieInfoDao.findByUsername(name);
//    }

	//yoxulogindenxglu
	
    public void changeStatus(Long id) {
        ShangpinleibieInfo shangpinleibieInfo = shangpinleibieInfoDao.selectByPrimaryKey(id);
        if(shangpinleibieInfo.getStatus().equals("是")){
            shangpinleibieInfo.setStatus("否");
            shangpinleibieInfoDao.updateByPrimaryKey(shangpinleibieInfo);
        }else if(shangpinleibieInfo.getStatus().equals("否")){
            shangpinleibieInfo.setStatus("是");
            shangpinleibieInfoDao.updateByPrimaryKey(shangpinleibieInfo);
        }
    }
	//ddaizdhifu
	//youtixing
	
	
	
	
	
	//xiugaimimazys
	
}
