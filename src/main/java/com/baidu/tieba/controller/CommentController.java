package com.baidu.tieba.controller;

import com.baidu.tieba.common.api.ApiResult;
import com.baidu.tieba.model.dto.CommentDTO;
import com.baidu.tieba.model.entity.Comment;
import com.baidu.tieba.model.entity.Post;
import com.baidu.tieba.model.entity.User;
import com.baidu.tieba.model.vo.CommentVO;
import com.baidu.tieba.service.CommentService;
import com.baidu.tieba.service.PostService;
import com.baidu.tieba.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

import static com.baidu.tieba.jwt.JwtUtil.USER_NAME;

@RequestMapping("/comment")
@RestController
public class CommentController extends BaseController{
    @Resource
    private CommentService commentService;
    @Resource
    private UserService userService;
    @Resource
    private PostService postService;

    @GetMapping("/get_comments")
    public ApiResult<Page<CommentVO>> getComments(  @RequestParam(value = "current",defaultValue = "1") Integer current,
                                                    @RequestParam(value = "size",defaultValue = "10") Integer size,
                                                    @RequestParam(value = "tab",defaultValue = "latest") String tab,
                                                    @RequestParam("topic_id") String topicId){
        Post post = postService.getById(topicId);
        String userId = post.getUserId();
        Page<CommentVO> page = commentService.getCommentsById(new Page<>(current, size), tab, userId, topicId);
        return ApiResult.success(page);
    }

    //添加评论
    @PostMapping("/add_comment")
    public ApiResult<String> addComment(@RequestHeader(USER_NAME) String username,
                           @RequestBody CommentDTO dto) throws InterruptedException {
        User user = userService.getUserByUsername(username);
        commentService.insertComment(user.getId(),dto);
        Post post = postService.getById(dto.getTopic_id());
        //帖子评论数加一
        post.setComments(post.getComments()+1);
        post.setModifyTime(new Date());
        postService.updateById(post);
        return ApiResult.success();
    }

    //删除评论
    @GetMapping("/delete_comment")
    public ApiResult<String> deleteComment(@RequestParam("commentId") String commentId){
        commentService.remove(new LambdaQueryWrapper<Comment>().eq(Comment::getId,commentId));
        return ApiResult.success();
    }
}
