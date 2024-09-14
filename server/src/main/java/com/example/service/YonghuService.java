package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.dao.ZhuceyonghuInfoDao;
import com.example.dao.YonghuDao;
import com.example.entity.Account;
import com.example.entity.ZhuceyonghuInfo;
import com.example.entity.Yonghu;
import com.example.exception.CustomException;
import com.example.vo.YonghuVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class YonghuService {

    @Resource
    private YonghuDao yonghuDao;
	@Resourceprivate ZhuceyonghuInfoDao zhuceyonghuInfoDao;

    /**
     * 新增用户
     */
    public Yonghu add(Yonghu yonghu) {
        // 唯一校验
        int count = yonghuDao.checkRepeat("name", yonghu.getName(), null);
        if (count > 0) {
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        if (yonghu.getPassword() == null) {
            // 默认密码123456
            yonghu.setPassword(SecureUtil.md5("123456"));
        } else {
            yonghu.setPassword(SecureUtil.md5(yonghu.getPassword()));
        }
        yonghuDao.insertSelective(yonghu);
        return yonghu;
    }

    public void delete(Long id) {
        yonghuDao.deleteByPrimaryKey(id);
    }

    public void update(Yonghu yonghu) {
        yonghuDao.updateByPrimaryKeySelective(yonghu);
    }

    public Yonghu findById(Long id) {
        Yonghu yonghu =yonghuDao.selectByPrimaryKey(id);
        if(yonghu.getLevel()!=null) {
			if (yonghu.getLevel().equals("注册用户")) {ZhuceyonghuInfo user = zhuceyonghuInfoDao.selectByPrimaryKey(yonghu.getUserId());yonghu.setCode(user.getZhanghao());yonghu.setUserName(user.getXingming()); }
        }
        return yonghu;
    }

    public Yonghu findByIdAndLevel(Long id, Integer level) {
        Example example = new Example(Yonghu.class);
        example.createCriteria().andEqualTo("id", id)
                .andEqualTo("level", level);
        List<Yonghu> list = yonghuDao.selectByExample(example);
        return list.get(0);
    }

    public List<Yonghu> findAll() {
        return yonghuDao.selectAll();
    }

    public PageInfo<Yonghu> findPage(Integer pageNum, Integer pageSize, Yonghu yonghu) {
        PageHelper.startPage(pageNum, pageSize);
        List<Yonghu> all = yonghuDao.findByCondition(yonghu);
        for (Yonghu yonghu1 : all) {
            if(yonghu1.getLevel()!=null){
				if(yonghu1.getLevel().equals("注册用户")){ZhuceyonghuInfo zhuceyonghuInfo = zhuceyonghuInfoDao.selectByPrimaryKey(yonghu1.getUserId());yonghu1.setUserName(zhuceyonghuInfo.getXingming());yonghu1.setCode(zhuceyonghuInfo.getZhanghao()); }
            }
        }
        return PageInfo.of(all);
    }

    /**
     * 登录
     */
    public Yonghu login(String name, String password) {
        List<Yonghu> list = yonghuDao.findByName(name);
        if (CollectionUtil.isEmpty(list)) {
            throw new CustomException(ResultCode.USER_NOT_EXIST_ERROR);
        }
        Yonghu yonghu = list.get(0);
        if(yonghu.getLevel() != null){
			if(yonghu.getLevel().equals("注册用户")) {if(yonghu.getUserId()!=null){ZhuceyonghuInfo user = zhuceyonghuInfoDao.selectByPrimaryKey(yonghu.getUserId());if(user != null) {yonghu.setCode(user.getZhanghao());yonghu.setUserName(user.getXingming());}}}
        }
        return list.get(0);
    }

    /**
     * 重置密码（忘记密码）
     */
    public Yonghu resetPassword(String name) {
        List<Yonghu> list = yonghuDao.findByName(name);
        if (CollectionUtil.isEmpty(list)) {
            throw new CustomException(ResultCode.USER_NOT_EXIST_ERROR);
        }
        update(list.get(0));
        return list.get(0);
    }

    /**
     * 修改密码
     */
    public boolean changePassword(Long id, String newPassword) {
        Yonghu yonghu = findById(id);
        if(yonghu == null) {
            throw new CustomException(ResultCode.USER_NOT_EXIST_ERROR);
        }
        update(yonghu);
        return true;
    }

    public Result bind(YonghuVo user) {
        Account account = new Account();
		if(user.getLevel().equals("注册用户")){account = zhuceyonghuInfoDao.selectByZhuceyonghuInfo(user);}
        if(account!=null){
            List<Yonghu> yonghu = yonghuDao.selectByUserId(account.getId());
            if(yonghu.size()==0){
                //唯一绑定
				if(user.getLevel().equals("注册用户")) {yonghuDao.bindZhuceyonghuInfo(user.getYonghuId(), account.getId());}
            }else {
                return Result.error("400","该人员已绑定，请勿多次绑定");
            }
        }else {
            return Result.error("400","该人员信息错误");
        }
        return Result.success();
    }

    public Yonghu resetPasswordById(Long id) {
        Yonghu yonghu = yonghuDao.selectByPrimaryKey(id);
        yonghu.setPassword(SecureUtil.md5("123456"));
        yonghuDao.updateByPrimaryKey(yonghu);
        return yonghu;
    }

    public void jiebang(Long id) {
        Yonghu yonghu = yonghuDao.selectByPrimaryKey(id);
        yonghu.setUserId(null);
        yonghu.setLevel(null);
        yonghuDao.updateByPrimaryKey(yonghu);
    }
}
