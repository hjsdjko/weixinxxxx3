package com.example.dao;

import com.example.entity.PaperCommentInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PaperCommentInfoDao extends Mapper<PaperCommentInfo> {

    List<PaperCommentInfo> findByContent(@Param("name") String name);

    @Select("select a.*,c.xingming as userName,c.zhaopian as avater from paper_comment_info as a left join zhuceyonghu_info as c on a.userId = c.id where paperId = #{paperId} and userId = #{userId}")
    List<PaperCommentInfo> findByGoodsIdAndUserId(@Param("paperId") Long paperId, @Param("userId") Long userId);

    @Select("select a.*,c.xingming as userName,c.zhaopian as avater from paper_comment_info as a left join zhuceyonghu_info as c on a.userId = c.id where paperId = #{paperId}")
    List<PaperCommentInfo> findByGoodsId(@Param("paperId") Long paperId);

    @Select("select count(id) from paper_comment_info")
    Integer count();
}
