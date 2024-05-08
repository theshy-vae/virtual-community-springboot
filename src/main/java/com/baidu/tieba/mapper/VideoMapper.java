package com.baidu.tieba.mapper;

import com.baidu.tieba.model.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoMapper extends BaseMapper<Video> {

    public List<Video> selectFourHotVideo();

}
