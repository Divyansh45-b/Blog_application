package com.divyanshCode.BlogApplication.helper;

import lombok.Data;

@Data
public class CommentDto {

    private Integer commentId;

    private String comment;

    private Integer postId;

    private Integer userId;

}
