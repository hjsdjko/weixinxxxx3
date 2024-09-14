package com.example.dao;

import com.example.entity.Account;
import com.example.entity.UserTextInfo;
import com.example.vo.UserTextInfoVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Repository
public interface UserTextInfoDao extends Mapper<UserTextInfo> {
    List<UserTextInfoVo> findByName(@Param("name") String name);


    List<UserTextInfoVo> findAllPageByUserId(HttpServletRequest request, String name);

    @Select("select * from user_text_info where userId = #{id} and level = #{level}")
    List<UserTextInfoVo> findAllPageByMy(Account account);


    @Select("select * from user_text_info")
    List<UserTextInfoVo> findAll();

    @Select("select t.*,u.xingming as userName,u.zhaopian as avater from `user_text_info` t join zhuceyonghu_info u on t.userId = u.id where t.userId = #{id} and t.level = #{level} and t.status = '审核通过'")
    List<UserTextInfo> findAllPageByUser(@Param("id") Long id, @Param("level") String level);

    @Select("select t.*,u.xingming as userName,u.zhaopian as avater from `user_text_info` t join zhuceyonghu_info u on t.userId = u.id where t.userId = #{id} and t.level = #{level} and t.status != '审核通过'")
    List<UserTextInfo> findAllPageByUser2(@Param("id") Long id, @Param("level") String level);

    @Select("select t.*,u.xingming as userName,u.zhaopian as avater from `user_text_info` t join zhuceyonghu_info u on t.userId = u.id where t.xuqiuId = #{xuqiuId} and t.status = '审核通过'")
    List<UserTextInfo> findByXuqiuId(Long xuqiuId);

    @Select("select t.*,u.xingming as userName,u.zhaopian as avater from `user_text_info` t join zhuceyonghu_info u on t.userId = u.id where t.id = #{foreignId}")
    UserTextInfo findByIdDetail(Long foreignId);

    @Select("select count(*) from user_text_info")
    Integer count();

    @Select("select title,click from user_text_info where status = '审核通过'")
    List<Map<String, Object>> getClickCount();

    @Select("select u.title,count(*) as count from user_text_info u join paper_comment_info p on u.id = p.paperId where u.status = '审核通过' group by p.paperId")
    List<Map<String, Object>> getCommentCount();

    @Select("select t.*,u.xingming as userName,u.zhaopian as avater from `user_text_info` t join zhuceyonghu_info u on t.userId = u.id where fenxiang = '是'")
    List<UserTextInfo> findFenxiang();
}
