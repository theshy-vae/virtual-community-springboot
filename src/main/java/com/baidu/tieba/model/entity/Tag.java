package com.baidu.tieba.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Builder
@TableName("tag")
@Accessors(chain = true)
public class Tag implements Serializable {
    private static final long serialVersionUID = 3257790983905872243L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField("name")
    private String name;
    /**
     * 当前标签下的话题个数
     */
    @TableField("topic_count")
    @Builder.Default
    private Integer topicCount = 1;

    //吧主
    @TableField("bigManager")
    @Builder.Default
    private String bigManager=null;

    //吧头像
    @TableField("image")
    @Builder.Default
    private String image="https://imgsa.baidu.com/forum/w%3D580/sign=f7ee5c474f086e066aa83f4332097b5a/97ee6e061d950a7b4d7f8eb209d162d9f3d3c961.jpg";

    //吧描述
    @TableField("description")
    @Builder.Default
    private String description="吧主很懒，没有写介绍";

    //关注数
    @TableField("followCount")
    @Builder.Default
    private Integer followCount=0;
}
