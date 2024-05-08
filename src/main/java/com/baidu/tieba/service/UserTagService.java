package com.baidu.tieba.service;

import com.baidu.tieba.model.entity.UserTag;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserTagService extends IService<UserTag> {
    boolean isFollow(String userId,String tagId);
}
