package com.divyanshCode.BlogApplication.Controller;


import com.divyanshCode.BlogApplication.Service.commentService;

import com.divyanshCode.BlogApplication.helper.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private commentService commentService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/user/{userId}/post/{postId}/create")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer userId , @PathVariable Integer postId)
    {

       CommentDto commentDto1 =  this.commentService.createComment(commentDto, postId, userId);
       return ResponseEntity.ok(commentDto1);
    }

    @PreAuthorize("hasRole('ADMIN')")
   @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer commentId)
   {
         this.commentService.deleteComment(commentId);
         return ResponseEntity.ok("deleted successfully");
   }

   @PreAuthorize("hasRole('ADMIN')")
   @GetMapping("/getCommentById/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable  Integer commentId)
   {
       CommentDto commentDto = this.commentService.getCommentById(commentId);
       return ResponseEntity.ok(commentDto);
   }

   @PreAuthorize("hasAnyRole('ADMIN','USER')")
   @GetMapping("/commentsByPostId/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable Integer postId)
   {
       List<CommentDto> commentDto = this.commentService.getCommentByPostId(postId);
       return ResponseEntity.ok(commentDto);
   }
}
