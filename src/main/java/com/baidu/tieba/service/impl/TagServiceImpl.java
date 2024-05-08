package com.baidu.tieba.service.impl;

import com.baidu.tieba.mapper.TagMapper;
import com.baidu.tieba.model.entity.Post;
import com.baidu.tieba.model.entity.Tag;
import com.baidu.tieba.model.entity.TopicTag;
import com.baidu.tieba.model.vo.MyTagVO;
import com.baidu.tieba.model.vo.ParentTagVO;
import com.baidu.tieba.model.vo.PostVO;
import com.baidu.tieba.service.TopicTagService;
import com.baidu.tieba.service.TagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Resource
    private TagService tagService;
    @Resource
    private TopicTagService topicTagService;


    @Override
    public Page<Post> selectTopicsByTagId(Page<Post> topicPage, String id) {
        return null;
    }

    @Override
    public Tag getTagByTagName(String tagName) {
        return baseMapper.selectOne(new LambdaQueryWrapper<Tag>().eq(Tag::getName,tagName));
    }

    @Override
    public Page<PostVO> getList(Page<PostVO> page, String tab, String search, String tagName) {
        Tag tag = tagService.getTagByTagName(tagName);
        String id=tag.getId();
        List<TopicTag> topicTagList = topicTagService.list(new LambdaQueryWrapper<TopicTag>().eq(TopicTag::getTagId, id));
        List<String> list=new ArrayList<>();
        topicTagList.forEach(item->{
            list.add(item.getTopicId());
        });
        //如果此贴吧中有帖子，就查询返回
        if(list.size()!=0){
            Map<String,Object> params=new HashMap<>();
            params.put("list",list);
            Page<PostVO> postVOPage=baseMapper.getList(page,tab,search,list);
            return postVOPage;
        }
        //如果没有，就返回空page
        else return page;
    }

    @Override
    public List<Tag> getHotTags() {
        List<Tag> hotTags = baseMapper.getHotTags();
        return hotTags;
    }

    @Override
    public List<ParentTagVO> getParentTags(String userId) {
        List<ParentTagVO> parentTags = baseMapper.getParentTags(userId);
        return parentTags;
    }

    @Override
    public List<MyTagVO> listMyTag(String username) {
        List<MyTagVO> list = baseMapper.listMyTag(username);
        return list;
    }


}
