package com.example.dao;
import com.example.entity.Account;
import com.example.entity.ShangpinxinxiInfo;

import com.example.vo.ShangpinxinxiInfoVo;
import com.example.vo.YonghuVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface ShangpinxinxiInfoDao extends Mapper<ShangpinxinxiInfo> {
    List<ShangpinxinxiInfoVo> findByShangpinmingcheng(@Param("shangpinmingcheng") String shangpinmingcheng);
	
	//xiugaimimazy
    int checkRepeat(@Param("column") String column, @Param("value") String value, @Param("id") Long id);
    
    Integer count();
	
	@Select("select * from shangpinxinxi_info where shangpinbianhao = #{shangpinbianhao}")
    ShangpinxinxiInfo selectByShangpinbianhao(String shangpinbianhao);
	
	@Select("SELECT * FROM shangpinxinxi_info order by id")
    List<Map<String, Object>> daochuexcel();
	
	@Select("SELECT xxdiquziduanxxxx,count(id) as hsggs FROM shangpinxinxi_info  group by xxdiquziduanxxxx")
    List<Map<String,String>> findByDiqu();
	
	
	
	@Select("SELECT distinct(shangpinleibie) as aa,count(id) as bb FROM shangpinxinxi_info group by shangpinleibie order by id")
List<Map<String, Object>> shangpinxinxi_tj_shangpinleibie();

	
	@Select("select count(id) from shangpinxinxi_info where kucun <= 50 ")
    Integer tixing();

    @Select("select * from shangpinxinxi_info where shangpinleibie = #{type}")
    List<ShangpinxinxiInfoVo> findByType(String type);


    @Select("select * from shangpinxinxi_info where shangpinmingcheng like concat('%', #{text}, '%')")
    List<ShangpinxinxiInfo> searchGoods(String text);





    //wexixinxzhuaxnyoxng
	
	

}
