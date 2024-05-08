package com.baidu.tieba.model.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class ProfileVO {
    /*id*/
    private String id;
    /*用户名*/
    private String username;
    /*昵称*/
    private String alias;
    /*头像*/
    private String avatar;
    /*注册时间*/
    private Date create_time;
    /*积分*/
    @Builder.Default
    private Integer score=0;
    /*关注数*/
    @Builder.Default
    private Integer followCount=0;
    /*关注者数*/
    @Builder.Default
    private Integer followerCount=0;
    /*文章数*/
    @Builder.Default
    private Integer topicCount=0;
    /*专栏数*/
    @Builder.Default
    private Integer columns=0;
    /*评论数*/
    @Builder.Default
    private Integer commentCount=0;
}
