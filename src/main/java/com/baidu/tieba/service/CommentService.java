package com.baidu.tieba.service;

import com.baidu.tieba.model.dto.CommentDTO;
import com.baidu.tieba.model.entity.Comment;
import com.baidu.tieba.model.vo.CommentVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CommentService extends IService<Comment> {
    public Page<CommentVO> getCommentsById(Page<Comment> page, String tab, String userId, String id);

    public void insertComment(String userId, CommentDTO dto);
}
