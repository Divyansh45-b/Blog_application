package com.divyanshCode.BlogApplication.helper;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PostDto {


    private int postId;

    @NotBlank(message = "content cannot be blank")
    private String content;

    @NotBlank(message = "title cannot be blank")
    private String title;

    private String imageName;

    private Date addDate;

    private UserSummaryDto userId;

    private CategorySummaryDto categoryId;

    private List<CommentDto> commentList = new ArrayList<>();
    ///all the comment can be shown when searching post.

}
