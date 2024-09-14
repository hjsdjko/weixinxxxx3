package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.ShangpinxinxiInfoDao;
//kuabiaojisuan1
import com.example.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.entity.ShangpinxinxiInfo;
//kuabiaojisuan2
import com.example.exception.CustomException;
import com.example.common.ResultCode;
import com.example.vo.ShangpinxinxiInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.crypto.SecureUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ShangpinxinxiInfoService {

    @Resource
    private ShangpinxinxiInfoDao shangpinxinxiInfoDao;
	
	
	//kuabiaojisuan3

    public ShangpinxinxiInfo add(ShangpinxinxiInfo shangpinxinxiInfo) {
        List<Long> tupianflst = shangpinxinxiInfo.getTupianflst();
        if (!CollectionUtil.isEmpty(tupianflst)) {
            shangpinxinxiInfo.setTupian(tupianflst.toString());
        }
		
		        //shangxchxuantupxian
        // 唯一校验
        
      // shangpinxinxiInfo.setAccount((double) 0);
	  
        shangpinxinxiInfoDao.insertSelective(shangpinxinxiInfo);
        return shangpinxinxiInfo;
    }
	
	public List<Map<String,String>> findByDiqu() {
        return shangpinxinxiInfoDao.findByDiqu();
    }

    public void delete(Long id) {
        shangpinxinxiInfoDao.deleteByPrimaryKey(id);
    }

    public void update(ShangpinxinxiInfo shangpinxinxiInfo) {
        List<Long> tupianflst = shangpinxinxiInfo.getTupianflst();
        if (!CollectionUtil.isEmpty(tupianflst)) {
            shangpinxinxiInfo.setTupian(tupianflst.toString());
        }
		
		        //shangxchxuantupxian
		//youdianzan
        shangpinxinxiInfoDao.updateByPrimaryKeySelective(shangpinxinxiInfo);
    }

    public ShangpinxinxiInfo findById(Long id) {
        return shangpinxinxiInfoDao.selectByPrimaryKey(id);
    }
	
    public List<ShangpinxinxiInfoVo> findAll() {
        return shangpinxinxiInfoDao.findByShangpinmingcheng("all");
    }

    public PageInfo<ShangpinxinxiInfoVo> findPage(String shangpinmingcheng, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<ShangpinxinxiInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        if (user.getLevel().equals("管理员")) {all = shangpinxinxiInfoDao.findByShangpinmingcheng(shangpinmingcheng);}
		
        
        return PageInfo.of(all);
    }
	
	public PageInfo<ShangpinxinxiInfoVo> findPageqt(String shangpinmingcheng, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<ShangpinxinxiInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        all = shangpinxinxiInfoDao.findByShangpinmingcheng(shangpinmingcheng);
		
	
	
        
        return PageInfo.of(all);
    }

   // public ShangpinxinxiInfoVo findByUserName(String name) {
//        return shangpinxinxiInfoDao.findByUsername(name);
//    }

	//yoxulogindenxglu
	
    public void changeStatus(Long id) {
        ShangpinxinxiInfo shangpinxinxiInfo = shangpinxinxiInfoDao.selectByPrimaryKey(id);
        if(shangpinxinxiInfo.getStatus().equals("是")){
            shangpinxinxiInfo.setStatus("否");
            shangpinxinxiInfoDao.updateByPrimaryKey(shangpinxinxiInfo);
        }else if(shangpinxinxiInfo.getStatus().equals("否")){
            shangpinxinxiInfo.setStatus("是");
            shangpinxinxiInfoDao.updateByPrimaryKey(shangpinxinxiInfo);
        }
    }
	//ddaizdhifu
	public Integer tixing() { return shangpinxinxiInfoDao.tixing(); }


    public List<ShangpinxinxiInfoVo> findByType(String type) {
        return shangpinxinxiInfoDao.findByType(type);
    }

    public List<ShangpinxinxiInfo> searchGoods(String text) {
        return shangpinxinxiInfoDao.searchGoods(text);
    }



    //xiugaimimazys
	
}
