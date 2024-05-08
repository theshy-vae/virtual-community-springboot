package com.baidu.tieba.controller;

import com.baidu.tieba.common.api.ApiResult;
import com.baidu.tieba.model.entity.Video;
import com.baidu.tieba.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Resource
    VideoService videoService;

    @RequestMapping("/list")
    public ApiResult<List<Video>> getVideoList(){
        List<Video> list = videoService.list();
        Collections.reverse(list);
        return ApiResult.success(list);
    }

    @RequestMapping("/listByName")
    public ApiResult<List<Video>> listByName(@RequestParam("search") String search){
        LambdaQueryWrapper<Video> w = new LambdaQueryWrapper<>();
        w.like(Video::getName,search);
        List<Video> list = videoService.list(w);
        Collections.reverse(list);
        return ApiResult.success(list);
    }

    //增加播放量
    @RequestMapping("/increaseCount")
    public ApiResult<String> increaseCount(@RequestParam("id") String id){
        Video video = videoService.getById(id);
        video.setCount(video.getCount()+1);
        videoService.updateById(video);
        return ApiResult.success();
    }

    @RequestMapping("/getHotList")
    public ApiResult<List<Video>> getHotList(){
        List<Video> list = videoService.selectFourHotVideo();
        Collections.reverse(list);
        return ApiResult.success(list);
    }

    @RequestMapping("/getVideoById")
    public ApiResult<Video> getVideoById(@RequestParam("id") String id){
        Video video = videoService.getById(id);
        return ApiResult.success(video);
    }

    @GetMapping("/deleteVideo")
    public ApiResult<String> deleteVideo(@RequestParam("id") String id){
        videoService.removeById(id);
        return ApiResult.success();
    }

    @PostMapping("/createVideo")
    public ApiResult<String> createVideo(@RequestBody Video video){
        video.setCount(0);
        video.setDanmuCount(0);
        videoService.save(video);
        return ApiResult.success();
    }
}
