package com.baidu.tieba.service;

import com.baidu.tieba.model.entity.Tag;
import com.baidu.tieba.model.entity.TopicTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TopicTagService extends IService<TopicTag> {
    void createTopicTag(List<Tag> tags, String id);
}
