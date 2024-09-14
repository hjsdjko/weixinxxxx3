package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.dao.PaperCommentInfoDao;
import com.example.entity.PaperCommentInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class PaperCommentInfoService {

    @Resource
    private PaperCommentInfoDao paperCommentInfoDao;

    public PaperCommentInfo add(PaperCommentInfo paperCommentInfo) {
        paperCommentInfo.setCreateTime(DateUtil.formatDateTime(new Date()));
        String content = paperCommentInfo.getContent();
        if (content.length() > 255) {
            paperCommentInfo.setContent(content.substring(0, 250));
        }
        paperCommentInfoDao.insertSelective(paperCommentInfo);
        return paperCommentInfo;
    }

    public void delete(Long id) {
        paperCommentInfoDao.deleteByPrimaryKey(id);
    }

    public void update(PaperCommentInfo paperCommentInfo) {
        String content = paperCommentInfo.getContent();
        if (content.length() > 255) {
            paperCommentInfo.setContent(content.substring(0, 250));
        }
        paperCommentInfoDao.updateByPrimaryKeySelective(paperCommentInfo);
    }

    public PaperCommentInfo findById(Long id) {
        return paperCommentInfoDao.selectByPrimaryKey(id);
    }

    public List<PaperCommentInfo> findAll() {
        return paperCommentInfoDao.selectAll();
    }

    public List<PaperCommentInfo> findAll(Long goodsId) {
        return paperCommentInfoDao.findByGoodsId(goodsId);
    }

    public PageInfo<PaperCommentInfo> findPage(Integer pageNum, Integer pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<PaperCommentInfo> all = paperCommentInfoDao.findByContent(name);
        return PageInfo.of(all);
    }

}
