package com.baidu.tieba.model.dto;

import lombok.Data;

@Data
public class PassDTO {
    private String oldPass;
    private String pass;
    private String checkPass;
}
