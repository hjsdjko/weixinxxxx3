package com.example.dao;
import com.example.entity.Account;
import com.example.entity.NongchanpinpinglunInfo;
import com.example.entity.ZhuceyonghuInfo;
import com.example.vo.NongchanpinpinglunInfoVo;
import com.example.vo.YonghuVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface NongchanpinpinglunInfoDao extends Mapper<NongchanpinpinglunInfo> {
    List<NongchanpinpinglunInfoVo> findByShangpinbianhao(@Param("shangpinbianhao") String shangpinbianhao);
	List<NongchanpinpinglunInfoVo> findByShangpinbianhaolist2(@Param("shangpinbianhao") String shangpinbianhao,@Param("zhanghao") String zhanghao);        
	//xiugaimimazy
    int checkRepeat(@Param("column") String column, @Param("value") String value, @Param("id") Long id);
    
    Integer count();
	
	@Select("select * from nongchanpinpinglun_info where shangpinbianhao = #{shangpinbianhao}")
    NongchanpinpinglunInfo selectByShangpinbianhao(String shangpinbianhao);
	
	@Select("SELECT * FROM nongchanpinpinglun_info order by id")
    List<Map<String, Object>> daochuexcel();
	
	@Select("SELECT xxdiquziduanxxxx,count(id) as hsggs FROM nongchanpinpinglun_info  group by xxdiquziduanxxxx")
    List<Map<String,String>> findByDiqu();
	
	
	
	
	
	
    
	
	
	
	//wexixinxzhuaxnyoxng
	
	@Select("select * from nongchanpinpinglun_info where zhanghao = #{zhanghao}")    List<NongchanpinpinglunInfo> findByzhanghao(ZhuceyonghuInfo zhuceyonghuInfo);    

}
