package com.example.dao;
import com.example.entity.Account;
import com.example.entity.ShangpinleibieInfo;

import com.example.vo.ShangpinleibieInfoVo;
import com.example.vo.YonghuVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface ShangpinleibieInfoDao extends Mapper<ShangpinleibieInfo> {
    List<ShangpinleibieInfoVo> findByLeibie(@Param("leibie") String leibie);
	
	//xiugaimimazy
    int checkRepeat(@Param("column") String column, @Param("value") String value, @Param("id") Long id);
    
    Integer count();
	
	@Select("select * from shangpinleibie_info where leibie = #{leibie}")
    ShangpinleibieInfo selectByLeibie(String leibie);
	
	@Select("SELECT * FROM shangpinleibie_info order by id")
    List<Map<String, Object>> daochuexcel();
	
	@Select("SELECT xxdiquziduanxxxx,count(id) as hsggs FROM shangpinleibie_info  group by xxdiquziduanxxxx")
    List<Map<String,String>> findByDiqu();
	
	
	
	
	
	
    
	
	
	
	//wexixinxzhuaxnyoxng
	
	

}
