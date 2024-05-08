package com.baidu.tieba.service;

import com.baidu.tieba.model.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface VideoService extends IService<Video> {

    public List<Video> selectFourHotVideo();

}
