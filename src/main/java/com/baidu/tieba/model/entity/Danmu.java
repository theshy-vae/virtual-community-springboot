package com.baidu.tieba.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Danmu {
    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private String danmu;
    private String time;
    private String color;
    private String isuse;
}
