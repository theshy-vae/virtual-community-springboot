package com.baidu.tieba.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baidu.tieba.common.api.ApiResult;
import com.baidu.tieba.jwt.JwtUtil;
import com.baidu.tieba.model.dto.LoginDTO;
import com.baidu.tieba.model.dto.PassDTO;
import com.baidu.tieba.model.dto.RegisterDTO;
import com.baidu.tieba.model.dto.UpdateDTO;
import com.baidu.tieba.model.entity.Tag;
import com.baidu.tieba.model.entity.User;
import com.baidu.tieba.model.entity.Validation;
import com.baidu.tieba.model.vo.*;
import com.baidu.tieba.service.*;
import com.baidu.tieba.utils.MD5Utils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;

import static com.baidu.tieba.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/ums/user")
public class UserController extends BaseController{
    @Resource
    UserService userService;
    @Resource
    TagService tagService;
    @Resource
    FollowService followService;
    @Resource
    FileUploadService fileUploadService;
    @Resource
    UserTagService userTagService;
    @Resource
    ValidationService validationService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ApiResult<Map<String,Object>> register(@Valid @RequestBody RegisterDTO dto){
        User user = userService.executeRegister(dto);
        if(user==null){
            return ApiResult.failed("用户名或邮箱已存在！");
        }
        Map<String,Object> map=new HashMap<>();
        map.put("user",user);
        return ApiResult.success(map);
    }
    //用户名密码登录
    @RequestMapping(value = "/loginAccount",method = RequestMethod.POST)
    public ApiResult<Map<String,String>> loginAccount(@RequestBody LoginDTO dto){
        String token = userService.executeLogin(dto);
        if(ObjectUtil.isEmpty(token)){
            return ApiResult.failed("用户名或密码错误");
        }
        Map<String,String> map=new HashMap<>();
        map.put("token",token);
        return ApiResult.success(map,"登陆成功");
    }

    //管理员登录
    @PostMapping("/adminLogin")
    public ApiResult<Map<String,String>> adminLogin(@RequestBody LoginDTO dto){
        String token = userService.executeAdminLogin(dto);
        if(ObjectUtil.isEmpty(token)){
            return ApiResult.failed("用户名或密码错误");
        }
        Map<String,String> map=new HashMap<>();
        map.put("token",token);
        return ApiResult.success(map,"登陆成功");
    }

    //发送邮箱验证码
    @GetMapping("/getEmailCode")
    public ApiResult<String> getEmailCode(String email){
        if (StringUtils.isBlank(email)){
            return ApiResult.failed("邮箱为空");
        }
        int result = userService.sendEmailCode(email);
        if(result==-1) return ApiResult.failed("当前验证码仍然有效");
        return ApiResult.success();
    }

    //邮箱登录
    @PostMapping("/loginEmail")
    @Transactional
    public ApiResult<Map<String,String>> loginEmail(@RequestBody LoginDTO dto){
        //判断验证码状态
        String email = dto.getEmail();
        String code = dto.getCode();
        LambdaQueryWrapper<Validation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Validation::getEmail,email)
                .eq(Validation::getCode,code).
                ge(Validation::getTime,new Date());//数据库中时间大于当前时间，说明验证码没过期
        Validation validation = validationService.getOne(wrapper);
        if(validation == null) return ApiResult.failed("验证码无效");
        if(!code.equals(validation.getCode())) return ApiResult.failed("验证码错误");

        //若此邮箱没有绑定用户，则创建用户
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getEmail,email);
        User user = userService.getOne(userLambdaQueryWrapper);
        if(user == null){
            user = new User();
            user.setEmail(email);
            user.setCreateTime(new Date());
            //随机设置用户名
            user.setUsername(UUID.randomUUID().toString().trim().replaceAll("-", ""));
            userService.save(user);
        }
        String token= JwtUtil.generateToken(String.valueOf(user.getUsername()));
        Map<String,String> map=new HashMap<>();
        map.put("token",token);
        return ApiResult.success(map,"登陆成功");
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ApiResult<User> getUser(@RequestHeader(value = USER_NAME) String userName) {
        User user = userService.getUserByUsername(userName);
        return ApiResult.success(user);
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ApiResult<Object> logOut(){
        return ApiResult.success(null,"注销成功");
    }

    //用户个人主页
    @GetMapping("/init")
    public ApiResult<Map<String,Object>> init(@RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                              @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                                              @RequestParam("username") String username){
        Page<PostVO> page = userService.init(new Page<PostVO>(pageNo, pageSize), username);
        ProfileVO profileVO = userService.initUser(username);
        String userId = profileVO.getId();
        //用户关注的吧列表
        List<ParentTagVO> parentTags = tagService.getParentTags(userId);
        //用户关注的用户列表
        List<ParentUserVO> parentUsers = followService.getParentUsers(userId);
        //用户粉丝列表
        List<ParentUserVO> followerUsers = followService.getFollowerUsers(userId);
        //创建的吧列表
        List<MyTagVO> list = tagService.listMyTag(username);

        Map<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("user",profileVO);
        map.put("parentTags",parentTags);
        map.put("parentUsers",parentUsers);
        map.put("followerUsers",followerUsers);
        map.put("myTagList",list);
        return ApiResult.success(map);
    }

    //用户信息设置初始化
    @PostMapping("/update")
    public ApiResult<Map<String,String>> updateUser(@RequestHeader(USER_NAME) String username,
                                                    @RequestBody UpdateDTO updateDTO){
        User user = userService.getUserByUsername(username);
        if(userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername,updateDTO.getUsername()).ne(User::getUsername,username))!=null){
            return ApiResult.failed("该用户名已被使用");
        }
        List<Tag> list = tagService.list(new LambdaQueryWrapper<Tag>().eq(Tag::getBigManager, username));
        list.forEach(tag -> {
            tag.setBigManager(updateDTO.getUsername());
            tagService.updateById(tag);
        });
        user.setUsername(updateDTO.getUsername());
        user.setEmail(updateDTO.getEmail());
        user.setAlias(username);
        userService.updateById(user);
        String token = JwtUtil.generateToken(updateDTO.getUsername());
        Map<String,String> map=new HashMap<>();
        map.put("user",user.toString());
        map.put("token",token);
        return ApiResult.success(map);
    }

    //修改密码
    @PostMapping("/updatePass")
    public ApiResult<String> updatePass(@RequestHeader(USER_NAME) String username,
                                        @RequestBody PassDTO passDTO){
        User user = userService.getUserByUsername(username);
        String pwd1 = MD5Utils.getPwd(passDTO.getPass());
        user.setPassword(pwd1);
        userService.updateById(user);
        return ApiResult.success("修改成功！");
    }

    //查找用户
    @RequestMapping("/search")
    public ApiResult<Page<User>> searchUser(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                            @RequestParam(value = "pageSize",defaultValue = "4") Integer pageSize,
                                            @RequestParam(value = "search") String search){
        Page<User> page = userService.page(new Page<>(pageNo, pageSize), new LambdaQueryWrapper<User>().like(User::getUsername, search));
        return ApiResult.success(page);
    }

    //更新用户头像
    @GetMapping("/uploadAvatar")
    public ApiResult<?> uploadAvatar(@RequestHeader(USER_NAME) String username,
                                     @RequestParam("url") String url){
        User user = userService.getUserByUsername(username);
        String oldUrl = user.getAvatar();
        fileUploadService.delete(oldUrl);
        user.setAvatar(url);
        userService.updateById(user);
        return ApiResult.success();
    }

    //获取用户信息
    @GetMapping("/getUserById")
    public ApiResult<User> getUserById(@RequestParam("id") String id){
        User user = userService.getById(id);
        return ApiResult.success(user);
    }

    //获取用户列表
    @GetMapping("/getUserList")
    public ApiResult<List<User>> getUserList(@RequestParam("key") String key){
        LambdaQueryWrapper<User> w = new LambdaQueryWrapper<>();
        w.like(User::getUsername,key).eq(User::getAdmin,0);
        List<User> list = userService.list(w);
        return ApiResult.success(list);
    }

    //删除用户
    @GetMapping("/deleteUserById")
    public ApiResult<String> deleteUserById(@RequestParam("id") String id){
        userService.removeById(id);
        return ApiResult.success();
    }
}
