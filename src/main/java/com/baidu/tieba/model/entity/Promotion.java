package com.baidu.tieba.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("promotion")
@Accessors(chain=true)
public class Promotion {
    private static final long serialVersionUID=1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String link;
    private String description;
}
