package com.baidu.tieba.service.impl;

import com.baidu.tieba.mapper.TopicTagMapper;
import com.baidu.tieba.model.entity.Tag;
import com.baidu.tieba.model.entity.TopicTag;
import com.baidu.tieba.service.TopicTagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TopicTagServiceImpl extends ServiceImpl<TopicTagMapper, TopicTag> implements TopicTagService {
    @Override
    public void createTopicTag(List<Tag> tags, String id) {
        this.baseMapper.delete(new LambdaQueryWrapper<TopicTag>().eq(TopicTag::getTopicId, id));
        tags.forEach(tag->{
            this.baseMapper.insert(TopicTag.builder().tagId(tag.getId()).topicId(id).build());
        });
    }
}
