package com.divyanshCode.BlogApplication.Service;

import com.divyanshCode.BlogApplication.Entity.Comment;
import com.divyanshCode.BlogApplication.Entity.Post;
import com.divyanshCode.BlogApplication.helper.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface commentService {


     CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);

     void deleteComment(Integer commentId);

     CommentDto getCommentById(Integer commentId);

     Page<CommentDto> getCommentByPostId(Integer postId, int page, int size);
}
