package com.baidu.tieba.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;


@Data
@TableName("post_tag")
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TopicTag implements Serializable {
    private static final long serialVersionUID = -5028599844989220715L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("tag_id")
    private String tagId;

    @TableField("topic_id")
    private String topicId;
}