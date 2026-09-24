package com.divyanshCode.BlogApplication.Service.impl;

import com.divyanshCode.BlogApplication.Entity.Comment;
import com.divyanshCode.BlogApplication.Entity.Post;
import com.divyanshCode.BlogApplication.Entity.User;
import com.divyanshCode.BlogApplication.Exception.ResourceNotFound;
import com.divyanshCode.BlogApplication.Repository.commentRepo;
import com.divyanshCode.BlogApplication.Repository.postRepo;
import com.divyanshCode.BlogApplication.Repository.userRepo;
import com.divyanshCode.BlogApplication.Service.commentService;
import com.divyanshCode.BlogApplication.helper.CommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentImpl  implements commentService {

    @Autowired
    private postRepo postRepo;

    @Autowired
    private commentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private userRepo userRepo;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("user not found with id : " + userId));
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("post not found with id: " + postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        comment.setUser(user);
        comment.setPost(post);

        Comment savedComment = this.commentRepo.save(comment);

        CommentDto dto = new CommentDto();

        dto.setCommentId(savedComment.getCommentId());
        dto.setComment(savedComment.getComment());
        dto.setPostId(savedComment.getPost().getPostId());
        dto.setUserId(savedComment.getUser().getUserId());

        return dto;
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFound("comment not found with id : " + commentId));

        this.commentRepo.delete(comment);
    }

    @Override
    public CommentDto getCommentById(Integer commentId) {

        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFound("comment not found with id " + commentId));
        CommentDto dto = new CommentDto();
        dto.setCommentId(comment.getCommentId());
        dto.setComment(comment.getComment());
        dto.setPostId(comment.getPost().getPostId());
        dto.setUserId(comment.getUser().getUserId());
        return dto;
    }


    @Override
    public Page<CommentDto> getCommentByPostId(Integer postId, int page, int size) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFound("post not found with id " + postId));

        Pageable pageable = PageRequest.of(page, size);

        Page<Comment> commentPage = this.commentRepo.findByPost(post, pageable);

        return commentPage.map(c -> {
            CommentDto dto = new CommentDto();
            dto.setCommentId(c.getCommentId());
            dto.setComment(c.getComment());
            dto.setPostId(c.getPost().getPostId());
            dto.setUserId(c.getUser().getUserId());
            return dto;
        });
    }
}
