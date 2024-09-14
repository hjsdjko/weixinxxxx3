package com.example.dao;
import com.example.entity.Account;
import com.example.entity.PinkunrenzhengshenqingInfo;
import com.example.entity.ZhuceyonghuInfo;
import com.example.vo.PinkunrenzhengshenqingInfoVo;
import com.example.vo.YonghuVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface PinkunrenzhengshenqingInfoDao extends Mapper<PinkunrenzhengshenqingInfo> {
    List<PinkunrenzhengshenqingInfoVo> findByShenqingbiaoti(@Param("shenqingbiaoti") String shenqingbiaoti);
	List<PinkunrenzhengshenqingInfoVo> findByShenqingbiaotilist2(@Param("shenqingbiaoti") String shenqingbiaoti,@Param("zhanghao") String zhanghao);        
	//xiugaimimazy
    int checkRepeat(@Param("column") String column, @Param("value") String value, @Param("id") Long id);
    
    Integer count();
	
	@Select("select * from pinkunrenzhengshenqing_info where shenqingbiaoti = #{shenqingbiaoti}")
    PinkunrenzhengshenqingInfo selectByShenqingbiaoti(String shenqingbiaoti);
	
	@Select("SELECT * FROM pinkunrenzhengshenqing_info order by id")
    List<Map<String, Object>> daochuexcel();
	
	@Select("SELECT xxdiquziduanxxxx,count(id) as hsggs FROM pinkunrenzhengshenqing_info  group by xxdiquziduanxxxx")
    List<Map<String,String>> findByDiqu();
	
	
	
	
	
	
    
	
	
	
	//wexixinxzhuaxnyoxng
	
	@Select("select * from pinkunrenzhengshenqing_info where zhanghao = #{zhanghao}")    List<PinkunrenzhengshenqingInfo> findByzhanghao(ZhuceyonghuInfo zhuceyonghuInfo);    

}
