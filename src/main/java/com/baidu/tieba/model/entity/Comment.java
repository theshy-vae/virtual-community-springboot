package com.baidu.tieba.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    //内容
    @NotBlank(message = "内容不能为空")
    @TableField(value = "content")
    private String content;

    //作者id
    @TableField("user_id")
    private String userId;

    //帖子id
    @TableField("topic_id")
    private String topicId;

    //楼数（第几楼）
    @TableField("floor")
    private Integer floor;

    //创建时间
    @TableField("create_time")
    private Date createTime;

    //修改时间
    @TableField("modify_time")
    private Date modifyTime;
}
