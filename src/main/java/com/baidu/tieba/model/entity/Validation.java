package com.baidu.tieba.model.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("validation")
public class Validation {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @TableField("email")
    private String email;
    @TableField("code")
    private String code;
    @TableField("type")
    private Integer type;
    @TableField("time")
    private Date time;
}
