package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.ZhuceyonghuInfoDao;
//kuabiaojisuan1
import com.example.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.entity.ZhuceyonghuInfo;
//kuabiaojisuan2
import com.example.exception.CustomException;
import com.example.common.ResultCode;
import com.example.vo.ZhuceyonghuInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.crypto.SecureUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ZhuceyonghuInfoService {

    @Resource
    private ZhuceyonghuInfoDao zhuceyonghuInfoDao;
	
	
	//kuabiaojisuan3

    public ZhuceyonghuInfo add(ZhuceyonghuInfo zhuceyonghuInfo) {
        List<Long> zhaopianflst = zhuceyonghuInfo.getZhaopianflst();
        if (!CollectionUtil.isEmpty(zhaopianflst)) {
            zhuceyonghuInfo.setZhaopian(zhaopianflst.toString());
        }
		
		        //shangxchxuantupxian
        // 唯一校验
        int count = zhuceyonghuInfoDao.checkRepeat("zhanghao", zhuceyonghuInfo.getZhanghao(), null);
if (count > 0) {
	throw new CustomException("1001", "该账号\"" + zhuceyonghuInfo.getZhanghao() + "\"已存在");
}
      // zhuceyonghuInfo.setAccount((double) 0);
	  
        zhuceyonghuInfoDao.insertSelective(zhuceyonghuInfo);
        return zhuceyonghuInfo;
    }
	
	public List<Map<String,String>> findByDiqu() {
        return zhuceyonghuInfoDao.findByDiqu();
    }

    public void delete(Long id) {
        zhuceyonghuInfoDao.deleteByPrimaryKey(id);
    }

    public void update(ZhuceyonghuInfo zhuceyonghuInfo) {
        List<Long> zhaopianflst = zhuceyonghuInfo.getZhaopianflst();
        if (!CollectionUtil.isEmpty(zhaopianflst)) {
            zhuceyonghuInfo.setZhaopian(zhaopianflst.toString());
        }
		
		        //shangxchxuantupxian
		//youdianzan
        zhuceyonghuInfoDao.updateByPrimaryKeySelective(zhuceyonghuInfo);
    }

    public ZhuceyonghuInfo findById(Long id) {
        return zhuceyonghuInfoDao.selectByPrimaryKey(id);
    }
	public ZhuceyonghuInfo findByZhanghao(String  zhanghao) { return zhuceyonghuInfoDao.selectByZhanghao(zhanghao); }
    public List<ZhuceyonghuInfoVo> findAll() {
        return zhuceyonghuInfoDao.findByXingming("all");
    }

    public PageInfo<ZhuceyonghuInfoVo> findPage(String xingming, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<ZhuceyonghuInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        if (user.getLevel().equals("管理员")) {all = zhuceyonghuInfoDao.findByXingming(xingming);}
		
        
        return PageInfo.of(all);
    }
	
	public PageInfo<ZhuceyonghuInfoVo> findPageqt(String xingming, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<ZhuceyonghuInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        all = zhuceyonghuInfoDao.findByXingming(xingming);
		
	
	
        
        return PageInfo.of(all);
    }

   // public ZhuceyonghuInfoVo findByUserName(String name) {
//        return zhuceyonghuInfoDao.findByUsername(name);
//    }

	public ZhuceyonghuInfo login(String zhanghao, String mima) {
        ZhuceyonghuInfo zhuceyonghuInfo = zhuceyonghuInfoDao.findByZhanghaozj(zhanghao);
        if (zhuceyonghuInfo == null) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        if (!SecureUtil.md5(mima).equalsIgnoreCase(zhuceyonghuInfo.getMima())) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return zhuceyonghuInfo;
    }
	
    public void changeStatus(Long id) {
        ZhuceyonghuInfo zhuceyonghuInfo = zhuceyonghuInfoDao.selectByPrimaryKey(id);
        if(zhuceyonghuInfo.getStatus().equals("是")){
            zhuceyonghuInfo.setStatus("否");
            zhuceyonghuInfoDao.updateByPrimaryKey(zhuceyonghuInfo);
        }else if(zhuceyonghuInfo.getStatus().equals("否")){
            zhuceyonghuInfo.setStatus("是");
            zhuceyonghuInfoDao.updateByPrimaryKey(zhuceyonghuInfo);
        }
    }
	//ddaizdhifu
	//youtixing
	 public void update2(ZhuceyonghuInfoVo zhuceyonghuInfo) {
        List<Long> zhaopianflst = zhuceyonghuInfo.getZhaopianflst();
        if (!CollectionUtil.isEmpty(zhaopianflst)) {
            zhuceyonghuInfo.setZhaopian(zhaopianflst.toString());
        }
		
		        //shangxchxuantupxian
        if (StringUtils.isEmpty(zhuceyonghuInfo.getMima())) {
            // 默认密码123456
            zhuceyonghuInfo.setMima(SecureUtil.md5("123456"));
        } else {
            zhuceyonghuInfo.setMima(SecureUtil.md5(zhuceyonghuInfo.getMima()));
        }
        zhuceyonghuInfoDao.updateByPrimaryKeySelective(zhuceyonghuInfo);
    }
	
	
	
	
	public boolean changeMima(String zhanghao, String newMima,String ymm) {    ZhuceyonghuInfo zhuceyonghuInfo = findByZhanghao(zhanghao);    if(zhuceyonghuInfo == null) { throw new CustomException(ResultCode.USER_NOT_EXIST_ERROR); }    else if(!SecureUtil.md5(ymm).equalsIgnoreCase(zhuceyonghuInfo.getMima())) { throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);}    zhuceyonghuInfo.setMima(SecureUtil.md5(newMima));    update(zhuceyonghuInfo);    return true;    }    
	
}
