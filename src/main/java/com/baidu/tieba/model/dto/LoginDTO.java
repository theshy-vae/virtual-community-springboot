package com.baidu.tieba.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class LoginDTO {
    private String username;
    private String password;
    private String email;
    private String code;
    private Boolean rememberMe;
}