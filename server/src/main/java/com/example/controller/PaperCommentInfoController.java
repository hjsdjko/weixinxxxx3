package com.example.controller;

import com.example.common.Result;
import com.example.entity.PaperCommentInfo;
import com.example.service.PaperCommentInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/paperCommentInfo")
public class PaperCommentInfoController {
    @Resource
    private PaperCommentInfoService paperCommentInfoService;

    @PostMapping
    public Result<PaperCommentInfo> add(@RequestBody PaperCommentInfo paperCommentInfo, HttpServletRequest request) {
        paperCommentInfoService.add(paperCommentInfo);
        return Result.success(paperCommentInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        paperCommentInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody PaperCommentInfo paperCommentInfo) {
        paperCommentInfoService.update(paperCommentInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<PaperCommentInfo> detail(@PathVariable Long id) {
        PaperCommentInfo paperCommentInfo = paperCommentInfoService.findById(id);
        return Result.success(paperCommentInfo);
    }

    @GetMapping
    public Result<List<PaperCommentInfo>> all() {
        return Result.success(paperCommentInfoService.findAll());
    }

    @GetMapping("/all/{videoId}")
    public Result<List<PaperCommentInfo>> all(@PathVariable Long videoId) {
        return Result.success(paperCommentInfoService.findAll(videoId));
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<PaperCommentInfo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   @PathVariable String name) {
        return Result.success(paperCommentInfoService.findPage(pageNum, pageSize, name));
    }
}
