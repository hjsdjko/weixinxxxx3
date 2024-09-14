package com.example.dao;
import com.example.entity.Account;
import com.example.entity.ZhuceyonghuInfo;

import com.example.vo.ZhuceyonghuInfoVo;
import com.example.vo.YonghuVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface ZhuceyonghuInfoDao extends Mapper<ZhuceyonghuInfo> {
    List<ZhuceyonghuInfoVo> findByXingming(@Param("xingming") String xingming);
	
	List<ZhuceyonghuInfo> findByZhanghao(@Param("zhanghao") String zhanghao);
    int checkRepeat(@Param("column") String column, @Param("value") String value, @Param("id") Long id);
    
    Integer count();
	
	@Select("select * from zhuceyonghu_info where zhanghao = #{zhanghao}")
    ZhuceyonghuInfo selectByZhanghao(String zhanghao);
	
	@Select("SELECT * FROM zhuceyonghu_info order by id")
    List<Map<String, Object>> daochuexcel();
	
	@Select("SELECT xxdiquziduanxxxx,count(id) as hsggs FROM zhuceyonghu_info  group by xxdiquziduanxxxx")
    List<Map<String,String>> findByDiqu();
	
	
	
	
	
	
    
	
	ZhuceyonghuInfoVo findByZhanghaozj(String zhanghao);
	
	@Select("select * from zhuceyonghu_info where xingming = #{name} and zhanghao = #{code}")Account selectByZhuceyonghuInfo(YonghuVo user);
	
	

}
