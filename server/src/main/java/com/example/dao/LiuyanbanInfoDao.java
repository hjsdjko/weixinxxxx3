package com.example.dao;

import com.example.entity.LiuyanbanInfo;
import com.example.vo.LiuyanbanInfoVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface LiuyanbanInfoDao extends Mapper<LiuyanbanInfo> {
    List<LiuyanbanInfoVo> findByBiaoti(@Param("biaoti") String biaoti);
	List<LiuyanbanInfoVo> findByBiaotilist2(@Param("biaoti") String biaoti,@Param("yonghuming") String yonghuming);        
    int checkRepeat(@Param("column") String column, @Param("value") String value, @Param("id") Long id);
    
    Integer count();
	
	@Select("select * from liuyanban_info where yonghuming = #{yonghuming}")
    LiuyanbanInfo selectByYonghuming(String yonghuming);
	
	
	
	
	
	
    
	
	

}
