package com.baidu.tieba.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentUserVO {
    private String id;
    private String username;
    private String avatar;
}
