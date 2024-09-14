package com.example.dao;

import com.example.entity.GonggaoxinxiInfo;
import com.example.vo.GonggaoxinxiInfoVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface GonggaoxinxiInfoDao extends Mapper<GonggaoxinxiInfo> {
    List<GonggaoxinxiInfoVo> findByGonggaobiaoti(@Param("gonggaobiaoti") String gonggaobiaoti);
	
    int checkRepeat(@Param("column") String column, @Param("value") String value, @Param("id") Long id);
    
    Integer count();
	
	@Select("select * from gonggaoxinxi_info where gonggaobiaoti = #{gonggaobiaoti}")
    GonggaoxinxiInfo selectByGonggaobiaoti(String gonggaobiaoti);
	
	@Select("SELECT * FROM gonggaoxinxi_info order by id")
    List<Map<String, Object>> daochuexcel();
	
	
	
	
	
	
    
	
	

}
