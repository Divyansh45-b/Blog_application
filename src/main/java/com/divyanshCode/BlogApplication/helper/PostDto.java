package com.divyanshCode.BlogApplication.helper;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PostDto {


    private int postId;

    private String content;

    private String title;

    private String imageName;

    private Date addDate;

    private UserSummaryDto userId;

    private CategorySummaryDto categoryId;

    private List<CommentDto> commentList = new ArrayList<>();
    ///all the comment can be shown when searching post.

}
