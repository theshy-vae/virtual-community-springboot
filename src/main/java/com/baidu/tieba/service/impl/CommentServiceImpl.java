package com.baidu.tieba.service.impl;

import com.baidu.tieba.mapper.CommentMapper;
import com.baidu.tieba.model.dto.CommentDTO;
import com.baidu.tieba.model.entity.Comment;
import com.baidu.tieba.model.vo.CommentVO;
import com.baidu.tieba.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public Page<CommentVO> getCommentsById(Page<Comment> page, String tab, String userId, String id) {
        Page<CommentVO> page1 = baseMapper.getCommentsById(page, tab, userId, id);
        return page1;
    }

    public void insertComment(String userId, CommentDTO dto){
        List<Comment> list = baseMapper.selectList(new LambdaQueryWrapper<Comment>().eq(Comment::getTopicId, dto.getTopic_id()));
        int floor=list.size()+2;
        Comment comment = Comment.builder()
                .content(dto.getContent())
                .floor(floor)
                .createTime(new Date())
                .modifyTime(new Date())
                .topicId(dto.getTopic_id())
                .userId(userId).build();
        baseMapper.insert(comment);
    }

}
