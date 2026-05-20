package com.divyanshCode.BlogApplication.Service;

import com.divyanshCode.BlogApplication.helper.CommentDto;

import java.util.List;


public interface commentService {


     CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);

     void deleteComment(Integer commentId);

     CommentDto getCommentById(Integer commentId);

     List<CommentDto> getCommentByPostId(Integer postId);
}
