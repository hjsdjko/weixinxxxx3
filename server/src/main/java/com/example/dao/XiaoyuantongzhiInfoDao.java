package com.example.dao;

import com.example.entity.XiaoyuantongzhiInfo;
import com.example.vo.XiaoyuantongzhiInfoVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface XiaoyuantongzhiInfoDao extends Mapper<XiaoyuantongzhiInfo> {
    List<XiaoyuantongzhiInfoVo> findByTongzhibianhao(@Param("tongzhibianhao") String tongzhibianhao);
	
    int checkRepeat(@Param("column") String column, @Param("value") String value, @Param("id") Long id);
    
    Integer count();
	
	@Select("select * from xiaoyuantongzhi_info where tongzhibianhao = #{tongzhibianhao}")
    XiaoyuantongzhiInfo selectByTongzhibianhao(String tongzhibianhao);
	
	@Select("SELECT * FROM xiaoyuantongzhi_info order by id")
    List<Map<String, Object>> daochuexcel();
	
	
	
	
	
	
    
	
	

}
