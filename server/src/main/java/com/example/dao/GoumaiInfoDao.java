package com.example.dao;

import com.example.entity.GoumaiInfo;
import com.example.vo.GoumaiInfoVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface GoumaiInfoDao extends Mapper<GoumaiInfo> {
    List<GoumaiInfoVo> findByShangpinmingcheng(@Param("shangpinmingcheng") String shangpinmingcheng);
	List<GoumaiInfoVo> findByShangpinmingchenglist2(@Param("shangpinmingcheng") String shangpinmingcheng,@Param("goumairen") String goumairen);
    List<GoumaiInfo> scdd(@Param("issh") String issh,@Param("goumairen") String goumairen);
    int checkRepeat(@Param("column") String column, @Param("value") String value, @Param("id") Long id);

    Integer count();

	@Select("select * from goumai_info where shangpinbianhao = #{shangpinbianhao}")
    GoumaiInfo selectByShangpinbianhao(String shangpinbianhao);

	@Select("SELECT * FROM goumai_info order by id")
    List<Map<String, Object>> daochuexcel();

    @Select("select * from goumai_info where goumairen = #{userId}")
    List<GoumaiInfoVo> selectByUserId(Long userId);

    @Update("update goumai_info set status = #{status} where id = #{id}")
    void updateStatus(Long id, String status);

    @Select("SELECT distinct(shangpinmingcheng) as aa,sum(goumaishuliang) as bb FROM goumai_info group by shangpinmingcheng order by id")
    List<Map<String, Object>> goumai_tj_goumaishuliang();
    @Select("SELECT distinct(shangpinmingcheng) as aa,sum(goumaijine) as bb FROM goumai_info group by shangpinmingcheng order by id")
    List<Map<String, Object>> goumai_tj_goumaijine();



}
