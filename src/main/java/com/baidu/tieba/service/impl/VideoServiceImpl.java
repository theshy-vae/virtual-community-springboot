package com.baidu.tieba.service.impl;

import com.baidu.tieba.mapper.VideoMapper;
import com.baidu.tieba.model.entity.Video;
import com.baidu.tieba.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Resource
    VideoMapper videoMapper;

    @Override
    public List<Video> selectFourHotVideo() {
        List<Video> list = videoMapper.selectFourHotVideo();
        return list;
    }
}
