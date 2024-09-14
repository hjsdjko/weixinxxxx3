package com.example.dao;

import com.example.entity.XinwentongzhiInfo;
import com.example.vo.XinwentongzhiInfoVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface XinwentongzhiInfoDao extends Mapper<XinwentongzhiInfo> {
    List<XinwentongzhiInfoVo> findByBiaoti(@Param("biaoti") String biaoti);
    List<XinwentongzhiInfoVo> findByBiaotilb(@Param("biaoti") String biaoti,@Param("leibie") String leibie);
	List<XinwentongzhiInfoVo> findByBiaotilist2(@Param("biaoti") String biaoti,@Param("faburen") String faburen);
        
    int checkRepeat(@Param("column") String column, @Param("value") String value, @Param("id") Long id);
    
    Integer count();
	
	@Select("select * from xinwentongzhi_info where faburen = #{faburen}")
    XinwentongzhiInfo selectByFaburen(String faburen);
	
	
	
	@Select("SELECT distinct(leibie) as aa,count(id) as bb FROM xinwentongzhi_info group by leibie order by id")
List<Map<String, Object>> xinwentongzhi_tj_leibie();

	
	
    
	
	

}
