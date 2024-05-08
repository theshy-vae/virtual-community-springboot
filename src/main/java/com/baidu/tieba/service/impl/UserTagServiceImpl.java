package com.baidu.tieba.service.impl;

import com.baidu.tieba.mapper.UserTagMapper;
import com.baidu.tieba.model.entity.UserTag;
import com.baidu.tieba.service.UserTagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserTagServiceImpl extends ServiceImpl<UserTagMapper, UserTag> implements UserTagService {
    @Override
    public boolean isFollow(String userId, String tagId) {
        UserTag userTag = baseMapper.selectOne(new LambdaQueryWrapper<UserTag>().eq(UserTag::getUserId, userId).eq(UserTag::getTagId, tagId));
        if(userTag != null) return true;
        else return false;
    }
}
