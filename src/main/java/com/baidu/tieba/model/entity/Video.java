package com.baidu.tieba.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Video {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String path;
    private String img_path;
    @Builder.Default
    private Integer count=0;
    @Builder.Default
    private Integer danmuCount=0;
}
