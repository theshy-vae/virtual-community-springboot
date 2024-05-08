package com.baidu.tieba.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDTO {

    private String content;

    private String topic_id;
}
