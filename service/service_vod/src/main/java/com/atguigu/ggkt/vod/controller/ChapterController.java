package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Chapter;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vo.vod.ChapterVo;
import com.atguigu.ggkt.vod.service.ChapterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-10-07
 */
@RestController
@RequestMapping("/admin/vod/chapter")

public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    //1 大纲列表（章节和小节）
    @ApiOperation("大纲列表")
    @GetMapping("getNestedTreeList/{courseId}")
    public Result getTreeList(@PathVariable Long courseId){
        List<ChapterVo> list =  chapterService.getTreeList(courseId);
        return Result.ok(list);
    }
    //2 添加章节的方法
    @PostMapping("save")
    public Result save(@RequestBody Chapter  chapter){
        chapterService.save(chapter);
        return Result.ok(null);
    }

    //3 修改 根据id
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id){
        Chapter chapter = chapterService.getById(id);
        return Result.ok(chapter);
    }
    //4 修改 最终实现
    @PostMapping("update")
    public Result update(@RequestBody Chapter  chapter){
        chapterService.updateById(chapter);
        return Result.ok(null);
    }
    //5 删除章节
    @DeleteMapping("remove/{id}")
    public Result save(@PathVariable Long id){
        chapterService.removeById(id);
        return Result.ok(null);
    }

}

