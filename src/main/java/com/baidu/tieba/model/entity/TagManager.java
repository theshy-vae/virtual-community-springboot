package com.baidu.tieba.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tag_smallManager")
public class TagManager implements Serializable {
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private String id;

    //小吧主id
    @TableField("userId")
    private String userId;

    //吧id
    @TableField("tagId")
    private String tagId;

}
