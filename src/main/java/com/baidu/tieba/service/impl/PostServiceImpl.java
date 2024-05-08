package com.baidu.tieba.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baidu.tieba.mapper.FollowMapper;
import com.baidu.tieba.mapper.TopicMapper;
import com.baidu.tieba.mapper.UserMapper;
import com.baidu.tieba.model.dto.CreateTopicDTO;
import com.baidu.tieba.model.entity.*;
import com.baidu.tieba.model.vo.PostVO;
import com.baidu.tieba.model.vo.ProfileVO;
import com.baidu.tieba.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PostServiceImpl extends ServiceImpl<TopicMapper, Post> implements PostService {
    @Resource
    private TagService tagService;
    @Resource
    private TopicTagService topicTagService;
    @Resource
    private UserMapper userMapper;
    @Resource
    FollowMapper followMapper;
    @Resource
    CommentService commentService;
    @Override
    public Page<PostVO> getList(Page<PostVO> page, String tab,String search) {
        // 查询话题
        Page<PostVO> iPage = this.baseMapper.selectListAndPage(page,tab,search);
        iPage.getRecords().forEach(postVO -> {
            TopicTag topicTag = topicTagService.getOne(new LambdaQueryWrapper<TopicTag>().eq(TopicTag::getTopicId, postVO.getId()));
            Tag tag = tagService.getById(topicTag.getTagId());
            System.out.println("111"+tag.getFollowCount());
            postVO.setTag(tag);
        });
        // 查询话题的标签
        return iPage;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)  //加上rollbackFor，非运行时类异常也能回滚
    public Post create(CreateTopicDTO dto, User user) {
        Post topic1 = this.baseMapper.selectOne(new LambdaQueryWrapper<Post>().eq(Post::getTitle, dto.getTitle()));
        if(ObjectUtil.isNotEmpty(topic1)){
            return null;
        }

        //插入帖子
        Post post = new Post().builder()
                .content(dto.getContent())
                .title(dto.getTitle())
                .userId(user.getId())
                .createTime(new Date())
                .modifyTime(new Date())
                .build();
        baseMapper.insert(post);

        //在pst_tag表中插入数据
        String tagName = dto.getTag();
        Tag tag = tagService.getTagByTagName(tagName);
        TopicTag topicTag = new TopicTag();
        topicTag.setTagId(tag.getId());
        topicTag.setTopicId(post.getId());
        topicTagService.save(topicTag);

        //更新用户积分
        userMapper.updateById(user.setScore(user.getScore()+1));

        return post;
    }

    @Override
    public Map<String, Object> viewTopic(String id) {
        Map<String,Object> map=new HashMap<>();
        Post post = this.baseMapper.selectById(id);
        if(ObjectUtil.isEmpty(post)){
            Assert.isNull(post,"帖子不存在");
        }
        //浏览次数加一
        post.setView(post.getView()+1);
        this.baseMapper.updateById(post);
        // emoji转码
        /*post.setContent(EmojiParser.parseToUnicode(post.getContent()));*/
        map.put("topic",post);

        //获取帖子的所有标签
        TopicTag topicTag = topicTagService.getOne(new LambdaQueryWrapper<TopicTag>().eq(TopicTag::getTopicId, id));
        Tag tag = tagService.getById(topicTag.getTagId());
        map.put("tag",tag);

        //帖子作者
        String userId = post.getUserId();
        User user = userMapper.selectById(userId);
        ProfileVO profileVO=ProfileVO.builder().build();
        BeanUtil.copyProperties(user,profileVO);
        profileVO.setTopicCount(user.getScore());
        List<Follow> follows = followMapper.selectList(new LambdaQueryWrapper<Follow>().eq(Follow::getParentId, user.getId()));
        profileVO.setFollowerCount(follows.size());
        map.put("user",profileVO);

        //帖子楼数
        int count = commentService.count(new LambdaQueryWrapper<Comment>().eq(Comment::getTopicId, id));
        map.put("count",count);
        return map;
    }

    @Override
    public List<Post> getRecommend(String id) {
        List<Post> recommend = baseMapper.getRecommend(id);
        return recommend;
    }

    @Override
    public Page<PostVO> searchByKey(String keyword, Page<PostVO> page) {
        return null;
    }
}
