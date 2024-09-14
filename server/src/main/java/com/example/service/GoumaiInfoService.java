package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.CartInfoDao;
import com.example.dao.GoumaiInfoDao;
import com.example.dao.ShangpinxinxiInfoDao;
import com.example.dao.YonghuDao;
import com.example.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.entity.GoumaiInfo;
import com.example.entity.ShangpinxinxiInfo;
import com.example.exception.CustomException;
import com.example.common.ResultCode;
import com.example.vo.GoumaiInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.crypto.SecureUtil;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class GoumaiInfoService {

    @Resource
    private GoumaiInfoDao goumaiInfoDao;

	@Resource
    private ShangpinxinxiInfoDao shangpinxinxiInfoDao;
	@Resource
    private CartInfoDao cartInfoDao;

	@Resource
    private YonghuDao yonghuDao;
    public GoumaiInfo add(GoumaiInfo goumaiInfo) {
        //shangxchxuantupxian
        // 唯一校验

      // goumaiInfo.setAccount((double) 0);
	    ShangpinxinxiInfo shangpinxinxiInfo = shangpinxinxiInfoDao.selectByShangpinbianhao(goumaiInfo.getShangpinbianhao());
        shangpinxinxiInfo.setKucun(String.valueOf(Integer.valueOf(shangpinxinxiInfo.getKucun())-Integer.valueOf(goumaiInfo.getGoumaishuliang())));
        shangpinxinxiInfo.setXiaoliang(String.valueOf(Integer.valueOf(shangpinxinxiInfo.getXiaoliang())+Integer.valueOf(goumaiInfo.getGoumaishuliang())));
        shangpinxinxiInfoDao.updateByPrimaryKeySelective(shangpinxinxiInfo);

        goumaiInfoDao.insertSelective(goumaiInfo);
        cartInfoDao.deleteGoods(goumaiInfo.getGoumairen(),shangpinxinxiInfo.getId());
        return goumaiInfo;
    }

    public void delete(Long id) {
        goumaiInfoDao.deleteByPrimaryKey(id);
    }

    public void update(GoumaiInfo goumaiInfo) {
        //shangxchxuantupxian
		//youdianzan
        goumaiInfoDao.updateByPrimaryKeySelective(goumaiInfo);
    }

    public GoumaiInfo findById(Long id) {
        return goumaiInfoDao.selectByPrimaryKey(id);
    }

    public List<GoumaiInfoVo> findAll() {
        return goumaiInfoDao.findByShangpinmingcheng("all");
    }

    public PageInfo<GoumaiInfoVo> findPage(String shangpinmingcheng, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);

		List<GoumaiInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        if (user.getLevel().equals("管理员")) {all = goumaiInfoDao.findByShangpinmingcheng(shangpinmingcheng);}
		if (user.getLevel().equals("注册用户")) {all = goumaiInfoDao.findByShangpinmingchenglist2(shangpinmingcheng,user.getZhanghao()); }
		//list3 if (user.getLevel().equals("cxcxx3")) {all = goumaiInfoDao.findByShangpinmingchenglist3(shangpinmingcheng,user.getdxzhujian3()); }
        for (GoumaiInfoVo goumaiInfoVo : all) {
            if(goumaiInfoVo.getGoumairen()!=null){
                Yonghu yonghu = yonghuDao.selectByPrimaryKey(goumaiInfoVo.getGoumairen());
                if(yonghu!=null){
                    goumaiInfoVo.setUserName(yonghu.getName());
                }
            }
        }

        return PageInfo.of(all);
    }

	public PageInfo<GoumaiInfoVo> findPageqt(String shangpinmingcheng, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);

		List<GoumaiInfoVo> all=null;
        Account user = (Account) request.getSession().getAttribute("user");
        all = goumaiInfoDao.findByShangpinmingcheng(shangpinmingcheng);
		//list2 if (user.getLevel().equals("cxcxx2")) {all = goumaiInfoDao.findByShangpinmingchenglist2(shangpinmingcheng,user.getdxzhujian2()); }
		//list3 if (user.getLevel().equals("cxcxx3")) {all = goumaiInfoDao.findByShangpinmingchenglist3(shangpinmingcheng,user.getdxzhujian3()); }


        return PageInfo.of(all);
    }

   // public GoumaiInfoVo findByUserName(String name) {
//        return goumaiInfoDao.findByUsername(name);
//    }

	//yoxulogindenxglu

    public void changeStatus(Long id,String status) {
        goumaiInfoDao.updateStatus(id, status);
    }
	//youtixing

    public List<GoumaiInfo> scdd(String issh,String goumairen) {
        List<GoumaiInfo> list = goumaiInfoDao.scdd(issh,goumairen);

        return list;
    }

    public List<GoumaiInfoVo> getByUserId(Long userId) {
        List<GoumaiInfoVo> goumaiInfoVos = goumaiInfoDao.selectByUserId(userId);
        for (GoumaiInfoVo goumaiInfoVo : goumaiInfoVos) {
            ShangpinxinxiInfo canpinxinxiInfo = shangpinxinxiInfoDao.selectByShangpinbianhao(goumaiInfoVo.getShangpinbianhao());
            goumaiInfoVo.setTupian(canpinxinxiInfo.getTupian());
        }
        return goumaiInfoVos;
    }
}
