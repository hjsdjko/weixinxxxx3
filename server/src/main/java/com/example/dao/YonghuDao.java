package com.example.dao;

import com.example.entity.Yonghu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface YonghuDao extends Mapper<Yonghu> {

    int checkRepeat(@Param("column") String column, @Param("value") String value, @Param("id") Long id);

    List<Yonghu> findByName(@Param("name") String name);

    @Select("select count(id) from yonghu")
    Integer count();

    List<Yonghu> findByCondition(Yonghu yonghu);

    @Select("select * from yonghu where userId = #{id}")
    List<Yonghu> selectByUserId(Long id);
	
	@Update("update yonghu set userId = #{userId} ,level = '注册用户' where id = #{yonghuId}")    void bindZhuceyonghuInfo(@Param("yonghuId") Long yonghuId,@Param("userId") Long id);			

}
