package com.baidu.tieba.service;

import com.baidu.tieba.model.entity.Follow;
import com.baidu.tieba.model.vo.ParentUserVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface FollowService extends IService<Follow> {
    List<ParentUserVO> getParentUsers(String userId);

    List<ParentUserVO> getFollowerUsers(String userId);
}
