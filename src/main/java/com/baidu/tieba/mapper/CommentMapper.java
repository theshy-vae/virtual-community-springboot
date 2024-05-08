package com.baidu.tieba.mapper;

import com.baidu.tieba.model.entity.Comment;
import com.baidu.tieba.model.vo.CommentVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface CommentMapper extends BaseMapper<Comment> {
    public Page<CommentVO> getCommentsById(Page<Comment> page, String tab, String userId, String id);
}
