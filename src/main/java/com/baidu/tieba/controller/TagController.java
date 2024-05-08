package com.baidu.tieba.controller;

import com.baidu.tieba.common.api.ApiResult;
import com.baidu.tieba.model.entity.*;
import com.baidu.tieba.model.vo.PostVO;
import com.baidu.tieba.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.baidu.tieba.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/tag")
public class TagController extends BaseController {

    @Resource
    private TagService tagService;
    @Resource
    private TopicTagService topicTagService;
    @Resource
    private PostService postService;
    @Resource
    private UserTagService userTagService;
    @Resource
    private UserService userService;
    @Resource
    private FileUploadService fileUploadService;
    @Resource
    private CommentService commentService;

    @GetMapping("/{name}")
    public ApiResult<Map<String,Object>> tag(@PathVariable("name") String name,
                                             @RequestParam(value="page",defaultValue = "1") Integer index,
                                             @RequestParam(value = "size",defaultValue = "10") Integer size){
        Map<String,Object> map=new HashMap();
        Tag tag = tagService.getOne(new LambdaQueryWrapper<Tag>().eq(Tag::getName, name));
        List<TopicTag> topicTags = topicTagService.list(new LambdaQueryWrapper<TopicTag>().eq(TopicTag::getTagId, tag.getId()));
        List<String> topicIds=new ArrayList<>();
        topicTags.forEach(item->{
            topicIds.add(item.getTopicId());
        });
        Page<Post> page = postService.page(new Page<Post>(index, size), new LambdaQueryWrapper<Post>().in(Post::getId, topicIds));
        map.put("topics",page);
        List<Tag> list = tagService.list(new LambdaQueryWrapper<Tag>().notIn(Tag::getName, name).orderByDesc(Tag::getTopicCount).last("limit 10"));
        map.put("hotTags",list);
        return ApiResult.success(map);
    }

    //用户是否关注此吧
    @GetMapping("/noToken_list")
    public ApiResult<Object> noToken_list(@RequestParam(value = "userId") String userId,
                                          @RequestParam(value = "tagName") String tagName){
        Tag tag = tagService.getTagByTagName(tagName);
        boolean follow = userTagService.isFollow(userId, tag.getId());
        return ApiResult.success(follow);
    }

    //吧信息初始化
    @GetMapping("/list")
    public ApiResult<Map<String,Object>> tagList(@RequestParam(value = "current",defaultValue = "1")  Integer pageNo,
                                                 @RequestParam(value = "size",defaultValue = "10")  Integer pageSize,
                                                 @RequestParam(value = "tab",defaultValue = "hot") String tab,
                                                 @RequestParam(value = "search",defaultValue = "") String search,
                                                 @RequestParam(value = "tagName") String tagName){

        Map<String,Object> map=new HashMap<>();
        Page<PostVO> list = tagService.getList(new Page<>(pageNo, pageSize), tab, search, tagName);
        Tag tag = tagService.getTagByTagName(tagName);
        //吧帖子列表
        map.put("list",list);
        //吧信息
        map.put("tag",tag);
        return ApiResult.success(map);
    }

    //关注/取关吧
    @GetMapping("/follow")
    public ApiResult<String> tagFollow(@RequestHeader(USER_NAME) String username,
                                       @RequestParam("follow") Boolean follow,
                                       @RequestParam("tagName") String tagName){
        User user = userService.getUserByUsername(username);
        Tag tag = tagService.getTagByTagName(tagName);
        if(follow){
            userTagService.remove(new LambdaQueryWrapper<UserTag>().eq(UserTag::getTagId,tag.getId()).eq(UserTag::getUserId,user.getId()));
            tag.setFollowCount(tag.getFollowCount()-1);
            tagService.updateById(tag);
        }else {
            UserTag userTag=new UserTag();
            userTag.setTagId(tag.getId());
            userTag.setUserId(user.getId());
            userTagService.save(userTag);
            tag.setFollowCount(tag.getFollowCount()+1);
            tagService.updateById(tag);
        }
        return ApiResult.success();
    }

    //获取热门贴吧
    @GetMapping("/hot")
    public ApiResult<List<Tag>> hotTags(){
        List<Tag> hotTags = tagService.getHotTags();
        return ApiResult.success(hotTags);
    }

    //搜索吧和用户
    @GetMapping("/search")
    public ApiResult<Page<Tag>> searchTagsAndUsers(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                                   @RequestParam(value = "pageSize",defaultValue = "4") Integer pageSize,
                                                   @RequestParam(value = "search") String search) {
        Page<Tag> page = tagService.page(new Page<>(pageNo, pageSize), new LambdaQueryWrapper<Tag>().like(Tag::getName, search));
        return ApiResult.success(page);
    }

    //创建吧
    @GetMapping("/create")
    public ApiResult<String> createTag(@RequestHeader(USER_NAME) String username,
                                       @RequestParam("name") String name,
                                       @RequestParam("description") String description,
                                       @RequestParam("avatar") String avatar){
        if(tagService.getOne(new LambdaQueryWrapper<Tag>().eq(Tag::getName,name))!=null){
            return ApiResult.failed("该吧已存在");
        }
        User user = userService.getUserByUsername(username);
        Tag tag = Tag.builder()
                .name(name)
                .image(avatar)
                .description(description)
                .bigManager(user.getUsername())
                .build();
        tagService.save(tag);
        return ApiResult.success();
    }

    //删除上传的吧头像
    @GetMapping("/deleteAvatar")
    public ApiResult<?> deleteAvatar(@RequestParam("oldUrl") String oldUrl){
        fileUploadService.delete(oldUrl);
        return ApiResult.success();
    }

    //删除吧
    @GetMapping("/deleteTag")
    public ApiResult<?> deleteTag(@RequestParam("tagId") String tagId){
        //删除用户关注的吧
        userTagService.remove(new LambdaQueryWrapper<UserTag>().eq(UserTag::getTagId,tagId));
        //删除此吧
        tagService.remove(new LambdaQueryWrapper<Tag>().eq(Tag::getId,tagId));
        List<TopicTag> topicTagList = topicTagService.list(new LambdaQueryWrapper<TopicTag>().eq(TopicTag::getTagId, tagId));
        List<String> postIdList=new ArrayList<>();
        topicTagList.forEach(topicTag -> {
            postIdList.add(topicTag.getTopicId());
        });
        postService.removeByIds(postIdList);
        postIdList.forEach(postId->{
            commentService.remove(new LambdaQueryWrapper<Comment>().eq(Comment::getTopicId,postId));
        });
        return ApiResult.success();
    }

    //更新吧头像
    @PostMapping("/updateAvatar")
    public ApiResult<?> updateAvatar(@RequestBody Tag tag){
        Tag oldTag = tagService.getById(tag.getId());
        String oldUrl = oldTag.getImage();
        //删除之前的图片
        fileUploadService.delete(oldUrl);
        tagService.updateById(tag);
        return ApiResult.success();
    }

    //修改吧简介
    @GetMapping("/updateDescription")
    public ApiResult<?> updateTagDescription(@RequestParam("tagId") String tagId,
                                             @RequestParam("description") String description){
        Tag tag = tagService.getById(tagId);
        tag.setDescription(description);
        tagService.updateById(tag);
        return ApiResult.success();
    }

    //获取社区列表
    @GetMapping("/getTagList")
    public ApiResult<List<Tag>> getTagList(@RequestParam("key") String key){
        LambdaQueryWrapper<Tag> w = new LambdaQueryWrapper<>();
        w.like(Tag::getName,key);
        List<Tag> list = tagService.list(w);
        return ApiResult.success(list);
    }
}