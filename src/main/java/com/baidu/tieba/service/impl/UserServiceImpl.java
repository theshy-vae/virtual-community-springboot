package com.baidu.tieba.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baidu.tieba.common.api.ValidationEnum;
import com.baidu.tieba.jwt.JwtUtil;
import com.baidu.tieba.mapper.UserMapper;
import com.baidu.tieba.model.dto.LoginDTO;
import com.baidu.tieba.model.dto.RegisterDTO;
import com.baidu.tieba.model.entity.*;
import com.baidu.tieba.model.vo.PostVO;
import com.baidu.tieba.model.vo.ProfileVO;
import com.baidu.tieba.service.*;
import com.baidu.tieba.utils.MD5Utils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    TopicTagService topicTagService;
    @Resource
    TagService tagService;
    @Resource
    FollowService followService;
    @Resource
    PostService postService;
    @Resource
    JavaMailSender javaMailSender;
    @Resource
    ValidationService validationService;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public User executeRegister(RegisterDTO dto) {
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper();
        wrapper.eq(User::getUsername,dto.getName()).or().eq(User::getEmail,dto.getEmail());
        User user = baseMapper.selectOne(wrapper);
        User addUser=null;
        if(user !=null){
            System.out.println("账号或邮箱已存在！");
        }else {
            addUser= User.builder()
                    .username(dto.getName())
                    .password(MD5Utils.getPwd(dto.getPass()))
                    .alias(dto.getName())
                    .email(dto.getEmail())
                    .createTime(new Date())
                    .status(true)
                    .build();
            baseMapper.insert(addUser);
        }
        return addUser;
    }

    @Override
    public User getUserByUsername(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    public String executeLogin(LoginDTO dto) {
        String token=null;
        try {
            User user = getUserByUsername(dto.getUsername());
            String encodePwd = MD5Utils.getPwd(dto.getPassword());
            if(!encodePwd.equals(user.getPassword())){
                throw new Exception("密码错误");
            }
            token= JwtUtil.generateToken(String.valueOf(user.getUsername()));
        } catch (Exception e) {
            log.warn("用户名或密码错误",dto.getUsername());
        }
        return token;
    }

    @Override
    public String executeAdminLogin(LoginDTO dto) {
        String token=null;
        try {
            User user = getUserByUsername(dto.getUsername());
            String encodePwd = MD5Utils.getPwd(dto.getPassword());
            if(!encodePwd.equals(user.getPassword())||user.getAdmin()==0){
                throw new Exception("用户名或密码错误");
            }
            token= JwtUtil.generateToken(String.valueOf(user.getUsername()));
        } catch (Exception e) {
            log.warn("用户名或密码错误",dto.getUsername());
        }
        return token;
    }

    @Override
    public Page<PostVO> init(Page<PostVO> page, String username) {
        Page<PostVO> iPage = baseMapper.init(page, username);
        iPage.getRecords().forEach(postVO -> {
            TopicTag topicTag = topicTagService.getOne(new LambdaQueryWrapper<TopicTag>().eq(TopicTag::getTopicId, postVO.getId()));
            Tag tag = tagService.getById(topicTag.getTagId());
            postVO.setTag(tag);
        });
        return iPage;
    }

    @Override
    public ProfileVO initUser(String username) {
        User user = baseMapper.selectByUsername(username);
        //粉丝数
        int followrs = followService.count(new LambdaQueryWrapper<Follow>().eq(Follow::getParentId, user.getId() ));
        //关注数
        int parents = followService.count(new LambdaQueryWrapper<Follow>().eq(Follow::getFollowerId, user.getId()));
        //文章数
        int count = postService.count(new LambdaQueryWrapper<Post>().eq(Post::getUserId, user.getId()));
        ProfileVO profileVO = ProfileVO.builder()
                .username(username)
                .alias(user.getAlias())
                .followCount(parents)
                .followerCount(followrs)
                .id(user.getId())
                .avatar(user.getAvatar())
                .topicCount(count)
                .score(user.getScore())
                .create_time(user.getCreateTime())
                .build();
        return profileVO;
    }

    @Override
    @Transactional
    public int sendEmailCode(String email) {
        Date now = new Date();
        //若数据库中的验证码有效，则不存储
        LambdaQueryWrapper<Validation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Validation::getEmail,email).eq(Validation::getType,ValidationEnum.LOGIN.getType())
                .ge(Validation::getTime,now);
        Validation validation = validationService.getOne(wrapper);
        if(validation!=null){
            return -1;
        }

        //发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject("【虚拟社区】登录邮箱验证");
        String code = RandomUtil.randomNumbers(4);
        message.setText("您的验证码是："+code+"，有效期为五分钟");
        message.setSentDate(now);
        message.setTo(email);
        javaMailSender.send(message);
        //发送完验证码后，将验证码与邮箱的存入数据库
        validationService.saveCode(email,code, ValidationEnum.LOGIN.getType(), DateUtil.offsetMinute(now,5));
        return 1;
    }
}
