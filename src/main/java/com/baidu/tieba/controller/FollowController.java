package com.baidu.tieba.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baidu.tieba.common.api.ApiResult;
import com.baidu.tieba.model.entity.Follow;
import com.baidu.tieba.model.entity.User;
import com.baidu.tieba.service.FollowService;
import com.baidu.tieba.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static com.baidu.tieba.jwt.JwtUtil.USER_NAME;

@RequestMapping("/relationship")
@RestController
public class FollowController extends BaseController{
    @Resource
    private UserService userService;
    @Resource
    private FollowService followService;

    @GetMapping("/subscribe/{id}")
    public ApiResult<String> follow(@RequestHeader(USER_NAME) String name,
                                    @PathVariable("id") String id){
        User user = userService.getUserByUsername(name);
        if(user.getId().equals(id)){
            return ApiResult.failed("你要不要点b脸，居然想关注自己");
        }
        //已经关注了
        else if(ObjectUtil.isNotEmpty(followService.getOne(new LambdaQueryWrapper<Follow>().eq(Follow::getFollowerId,user.getId())
                .eq(Follow::getParentId,id)))){
            return ApiResult.failed("你已经关注了");
        }
        else {
            Follow follow = new Follow();
            follow.setFollowerId(user.getId());
            follow.setParentId(id);
            followService.save(follow);
            return ApiResult.success("已关注");
        }
    }

    @GetMapping("/unsubscribe/{id}")
    public ApiResult<String> unfollow(@RequestHeader(USER_NAME) String username,
                                      @PathVariable("id") String id){
        User user = userService.getUserByUsername(username);
        //已经关注了
        followService.remove(new LambdaQueryWrapper<Follow>().eq(Follow::getFollowerId,user.getId())
                    .eq(Follow::getParentId,id));
        return ApiResult.success("已取消关注");
    }

    @GetMapping("validate/{topicUserId}")
    private ApiResult<Map<String,Object>> hasFollow(@RequestHeader(USER_NAME) String username,
                                     @PathVariable("topicUserId") String id){
        Map<String,Object> map=new HashMap<>();
        map.put("hasFollow",false);
        User user = userService.getUserByUsername(username);
        if(ObjectUtil.isNotEmpty(followService.getOne(new LambdaQueryWrapper<Follow>().eq(Follow::getFollowerId,user.getId())
                .eq(Follow::getParentId,id)))) {
            map.put("hasFollow",true);
        }
        return ApiResult.success(map);
    }

}
