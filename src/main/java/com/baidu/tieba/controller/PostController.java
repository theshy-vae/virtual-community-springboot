package com.baidu.tieba.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baidu.tieba.common.api.ApiResult;
import com.baidu.tieba.model.dto.CreateTopicDTO;
import com.baidu.tieba.model.entity.Comment;
import com.baidu.tieba.model.entity.Post;
import com.baidu.tieba.model.entity.TopicTag;
import com.baidu.tieba.model.entity.User;
import com.baidu.tieba.model.vo.PostVO;
import com.baidu.tieba.service.CommentService;
import com.baidu.tieba.service.TopicTagService;
import com.baidu.tieba.service.PostService;
import com.baidu.tieba.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.baidu.tieba.jwt.JwtUtil.USER_NAME;


@RestController
@RequestMapping("/post")
public class PostController extends BaseController {

    @Resource
    private PostService postService;
    @Resource
    private UserService userService;
    @Resource
    private CommentService commentService;
    @Resource
    private TopicTagService topicTagService;

    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                                        @RequestParam(value = "search",defaultValue = "") String search) {
        Page<PostVO> list = postService.getList(new Page<>(pageNo, pageSize), tab,search);
        return ApiResult.success(list);
    }

    @PostMapping("/create")
    public ApiResult<Post> create(@RequestHeader(value = USER_NAME) String username, @RequestBody CreateTopicDTO dto){
        User user = userService.getUserByUsername(username);
        Post post = postService.create(dto, user);
        if(ObjectUtil.isEmpty(post)) return ApiResult.failed("标题已存在");
        return ApiResult.success(post);
    }

    @GetMapping()
    public ApiResult<Map<String,Object>> view(@RequestParam("id") String id){
        Map<String, Object> map = postService.viewTopic(id);
        return ApiResult.success(map);
    }

    @GetMapping("/recommend")
    public ApiResult<List<Post>> getRecommend(@RequestParam("topicId") String id){
        List<Post> recommend = postService.getRecommend(id);
        return ApiResult.success(recommend);
    }

    @GetMapping("/delete_post")
    public ApiResult<String> deletePost(@RequestParam("id") String id){
        postService.remove(new LambdaQueryWrapper<Post>().eq(Post::getId,id));
        commentService.remove(new LambdaQueryWrapper<Comment>().eq(Comment::getTopicId,id));
        topicTagService.remove(new LambdaQueryWrapper<TopicTag>().eq(TopicTag::getTopicId,id));
        return ApiResult.success();
    }

    @PostMapping("/update_post")
    public ApiResult<String> updatePost(@RequestBody Post post){
        post.setModifyTime(new Date());
        postService.updateById(post);
        return ApiResult.success(post.getId());
    }

}