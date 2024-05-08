package com.baidu.tieba.controller;

import com.baidu.tieba.common.api.ApiResult;
import com.baidu.tieba.model.entity.Danmu;
import com.baidu.tieba.model.entity.Video;
import com.baidu.tieba.service.DanmuService;
import com.baidu.tieba.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/danmu")
public class DanmuController {

    @Resource
    DanmuService danmuService;

    @Resource
    VideoService videoService;

    @RequestMapping("/sendDM")
    public ApiResult<String> sendDM(@RequestBody Danmu danmu){
        danmu.setIsuse("0");
        danmuService.save(danmu);
        String id = danmu.getId();
        //视频弹幕数加一
        Video video = videoService.getById(id);
        video.setDanmuCount(video.getDanmuCount()+1);
        videoService.updateById(video);
        return ApiResult.success();
    }

    @GetMapping("/getDMById")
    public ApiResult<List<Danmu>> getDMById(@RequestParam("id") String id){
        LambdaQueryWrapper<Danmu> w = new LambdaQueryWrapper<>();
        w.eq(Danmu::getId,id);
        List<Danmu> list = danmuService.list(w);
        return ApiResult.success(list);
    }

}
