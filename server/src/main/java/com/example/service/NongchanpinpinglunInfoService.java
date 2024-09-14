package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.NongchanpinpinglunInfoDao;
//kuabiaojisuan1
import com.example.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.entity.NongchanpinpinglunInfo;
//kuabiaojisuan2
import com.example.exception.CustomException;
import com.example.common.ResultCode;
import com.example.vo.NongchanpinpinglunInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.crypto.SecureUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class NongchanpinpinglunInfoService {

    @Resource
    private NongchanpinpinglunInfoDao nongchanpinpinglunInfoDao;
	@Resource    private ZhuceyonghuInfoService zhuceyonghuInfoService;    
	
	//kuabiaojisuan3

    public NongchanpinpinglunInfo add(NongchanpinpinglunInfo nongchanpinpinglunInfo) {
        //shangxchxuantupxian
        // 唯一校验
        
      // nongchanpinpinglunInfo.setAccount((double) 0);
	  
        nongchanpinpinglunInfoDao.insertSelective(nongchanpinpinglunInfo);
        return nongchanpinpinglunInfo;
    }
	
	public List<Map<String,String>> findByDiqu() {
        return nongchanpinpinglunInfoDao.findByDiqu();
    }

    public void delete(Long id) {
        nongchanpinpinglunInfoDao.deleteByPrimaryKey(id);
    }

    public void update(NongchanpinpinglunInfo nongchanpinpinglunInfo) {
        //shangxchxuantupxian
		//youdianzan
        nongchanpinpinglunInfoDao.updateByPrimaryKeySelective(nongchanpinpinglunInfo);
    }

    public NongchanpinpinglunInfo findById(Long id) {
        return nongchanpinpinglunInfoDao.selectByPrimaryKey(id);
    }
	
    public List<NongchanpinpinglunInfoVo> findAll() {
        return nongchanpinpinglunInfoDao.findByShangpinbianhao("all");
    }

    public PageInfo<NongchanpinpinglunInfoVo> findPage(String shangpinbianhao, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<NongchanpinpinglunInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        if (user.getLevel().equals("管理员")) {all = nongchanpinpinglunInfoDao.findByShangpinbianhao(shangpinbianhao);}
		if (user.getLevel().equals("注册用户")) {all = nongchanpinpinglunInfoDao.findByShangpinbianhaolist2(shangpinbianhao,user.getZhanghao()); }        
        
        return PageInfo.of(all);
    }
	
	public PageInfo<NongchanpinpinglunInfoVo> findPageqt(String shangpinbianhao, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
		
		List<NongchanpinpinglunInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        all = nongchanpinpinglunInfoDao.findByShangpinbianhao(shangpinbianhao);
		if (user.getLevel().equals("注册用户")) {all = nongchanpinpinglunInfoDao.findByShangpinbianhaolist2(shangpinbianhao,user.getZhanghao()); }        
	
	
        
        return PageInfo.of(all);
    }

   // public NongchanpinpinglunInfoVo findByUserName(String name) {
//        return nongchanpinpinglunInfoDao.findByUsername(name);
//    }

	//yoxulogindenxglu
	
    public void changeStatus(Long id) {
        NongchanpinpinglunInfo nongchanpinpinglunInfo = nongchanpinpinglunInfoDao.selectByPrimaryKey(id);
        if(nongchanpinpinglunInfo.getStatus().equals("是")){
            nongchanpinpinglunInfo.setStatus("否");
            nongchanpinpinglunInfoDao.updateByPrimaryKey(nongchanpinpinglunInfo);
        }else if(nongchanpinpinglunInfo.getStatus().equals("否")){
            nongchanpinpinglunInfo.setStatus("是");
            nongchanpinpinglunInfoDao.updateByPrimaryKey(nongchanpinpinglunInfo);
        }
    }
	//ddaizdhifu
	//youtixing
	
	
	
	public List<NongchanpinpinglunInfo> getByzhuceyonghuZhanghao(String zhanghao) {    ZhuceyonghuInfo zhuceyonghuInfo = zhuceyonghuInfoService.findByZhanghao(zhanghao);    return nongchanpinpinglunInfoDao.findByzhanghao(zhuceyonghuInfo);    }    
	
	//xiugaimimazys
	
}
