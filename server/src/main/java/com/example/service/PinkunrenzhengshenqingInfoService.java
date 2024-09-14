package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.PinkunrenzhengshenqingInfoDao;
//kuabiaojisuan1
import com.example.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.entity.PinkunrenzhengshenqingInfo;
//kuabiaojisuan2
import com.example.exception.CustomException;
import com.example.common.ResultCode;
import com.example.vo.PinkunrenzhengshenqingInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.crypto.SecureUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PinkunrenzhengshenqingInfoService {

    @Resource
    private PinkunrenzhengshenqingInfoDao pinkunrenzhengshenqingInfoDao;
	@Resource    private ZhuceyonghuInfoService zhuceyonghuInfoService;    
	
	//kuabiaojisuan3

    public PinkunrenzhengshenqingInfo add(PinkunrenzhengshenqingInfo pinkunrenzhengshenqingInfo) {
        List<Long> pinkunzhengshuflst = pinkunrenzhengshenqingInfo.getPinkunzhengshuflst();
        if (!CollectionUtil.isEmpty(pinkunzhengshuflst)) {
            pinkunrenzhengshenqingInfo.setPinkunzhengshu(pinkunzhengshuflst.toString());
        }
		
		        //shangxchxuantupxian
        // 唯一校验
        
      // pinkunrenzhengshenqingInfo.setAccount((double) 0);
	  
        pinkunrenzhengshenqingInfoDao.insertSelective(pinkunrenzhengshenqingInfo);
        return pinkunrenzhengshenqingInfo;
    }
	
	public List<Map<String,String>> findByDiqu() {
        return pinkunrenzhengshenqingInfoDao.findByDiqu();
    }

    public void delete(Long id) {
        pinkunrenzhengshenqingInfoDao.deleteByPrimaryKey(id);
    }

    public void update(PinkunrenzhengshenqingInfo pinkunrenzhengshenqingInfo) {
        List<Long> pinkunzhengshuflst = pinkunrenzhengshenqingInfo.getPinkunzhengshuflst();
        if (!CollectionUtil.isEmpty(pinkunzhengshuflst)) {
            pinkunrenzhengshenqingInfo.setPinkunzhengshu(pinkunzhengshuflst.toString());
        }
		
		        //shangxchxuantupxian
		//youdianzan
        pinkunrenzhengshenqingInfoDao.updateByPrimaryKeySelective(pinkunrenzhengshenqingInfo);
    }

    public PinkunrenzhengshenqingInfo findById(Long id) {
        return pinkunrenzhengshenqingInfoDao.selectByPrimaryKey(id);
    }
	
    public List<PinkunrenzhengshenqingInfoVo> findAll() {
        return pinkunrenzhengshenqingInfoDao.findByShenqingbiaoti("all");
    }

    public PageInfo<PinkunrenzhengshenqingInfoVo> findPage(String shenqingbiaoti, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<PinkunrenzhengshenqingInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        if (user.getLevel().equals("管理员")) {all = pinkunrenzhengshenqingInfoDao.findByShenqingbiaoti(shenqingbiaoti);}
		if (user.getLevel().equals("注册用户")) {all = pinkunrenzhengshenqingInfoDao.findByShenqingbiaotilist2(shenqingbiaoti,user.getZhanghao()); }        
        
        return PageInfo.of(all);
    }
	
	public PageInfo<PinkunrenzhengshenqingInfoVo> findPageqt(String shenqingbiaoti, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<PinkunrenzhengshenqingInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        all = pinkunrenzhengshenqingInfoDao.findByShenqingbiaoti(shenqingbiaoti);
		if (user.getLevel().equals("注册用户")) {all = pinkunrenzhengshenqingInfoDao.findByShenqingbiaotilist2(shenqingbiaoti,user.getZhanghao()); }        
	
	
        
        return PageInfo.of(all);
    }

   // public PinkunrenzhengshenqingInfoVo findByUserName(String name) {
//        return pinkunrenzhengshenqingInfoDao.findByUsername(name);
//    }

	//yoxulogindenxglu
	
    public void changeStatus(Long id) {
        PinkunrenzhengshenqingInfo pinkunrenzhengshenqingInfo = pinkunrenzhengshenqingInfoDao.selectByPrimaryKey(id);
        if(pinkunrenzhengshenqingInfo.getStatus().equals("是")){
            pinkunrenzhengshenqingInfo.setStatus("否");
            pinkunrenzhengshenqingInfoDao.updateByPrimaryKey(pinkunrenzhengshenqingInfo);
        }else if(pinkunrenzhengshenqingInfo.getStatus().equals("否")){
            pinkunrenzhengshenqingInfo.setStatus("是");
            pinkunrenzhengshenqingInfoDao.updateByPrimaryKey(pinkunrenzhengshenqingInfo);
        }
    }
	//ddaizdhifu
	//youtixing
	
	
	
	public List<PinkunrenzhengshenqingInfo> getByzhuceyonghuZhanghao(String zhanghao) {    ZhuceyonghuInfo zhuceyonghuInfo = zhuceyonghuInfoService.findByZhanghao(zhanghao);    return pinkunrenzhengshenqingInfoDao.findByzhanghao(zhuceyonghuInfo);    }    
	
	//xiugaimimazys
	
}
