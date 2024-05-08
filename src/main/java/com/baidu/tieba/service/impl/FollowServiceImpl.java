package com.baidu.tieba.service.impl;

import com.baidu.tieba.mapper.FollowMapper;
import com.baidu.tieba.model.entity.Follow;
import com.baidu.tieba.model.entity.User;
import com.baidu.tieba.model.vo.ParentUserVO;
import com.baidu.tieba.service.FollowService;
import com.baidu.tieba.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {
    @Resource
    UserService userService;

    @Override
    public List<ParentUserVO> getParentUsers(String userId) {
        List<Follow> follows = baseMapper.selectList(new LambdaQueryWrapper<Follow>().eq(Follow::getFollowerId, userId));
        List<ParentUserVO> list=new ArrayList<>();
        follows.forEach(item->{
            ParentUserVO parentUserVO=new ParentUserVO();
            User user = userService.getById(item.getParentId());
            parentUserVO.setId(user.getId());
            parentUserVO.setUsername(user.getUsername());
            parentUserVO.setAvatar(user.getAvatar());
            list.add(parentUserVO);
        });
        return list;
    }

    @Override
    public List<ParentUserVO> getFollowerUsers(String userId) {
        List<Follow> follows = baseMapper.selectList(new LambdaQueryWrapper<Follow>().eq(Follow::getParentId, userId));
        List<ParentUserVO> list=new ArrayList<>();
        follows.forEach(item->{
            ParentUserVO parentUserVO=new ParentUserVO();
            User user = userService.getById(item.getFollowerId());
            parentUserVO.setId(user.getId());
            parentUserVO.setUsername(user.getUsername());
            parentUserVO.setAvatar(user.getAvatar());
            list.add(parentUserVO);
        });
        return list;
    }
}
