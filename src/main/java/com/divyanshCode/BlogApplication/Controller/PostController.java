package com.divyanshCode.BlogApplication.Controller;
import com.divyanshCode.BlogApplication.Service.postService;
import com.divyanshCode.BlogApplication.helper.PostDto;
import com.divyanshCode.BlogApplication.helper.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post/")
public class PostController {

    @Autowired
    private postService postService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/user/{id}/category/{category_id}/createPost")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer id,
                                              @PathVariable Integer category_id) {

       PostDto newPost = this.postService.createPost(postDto,id,category_id);

       return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

    ///to avoid hard coded values in our project instead
   /// of writing default value again and again use another class for constant and use it here using . keyword

   @GetMapping("/getAllPost")
   public ResponseEntity<PostResponse> getAllPost(@RequestParam(defaultValue = "0") int pageNum,
                                                  @RequestParam(defaultValue = "6") int pageSize,
                                                  @RequestParam (defaultValue = "postId")String sortBy)
   {
      PostResponse allPost = this.postService.getAllPost(pageNum , pageSize, sortBy);
      return ResponseEntity.ok().body(allPost);
   }

   @PreAuthorize("hasRole('ADMIN')")
   @DeleteMapping("/delete/{postId}")
   public ResponseEntity<String> deletePost(@PathVariable Integer postId)
   {
       this.postService.deletePost(postId);
       return ResponseEntity.ok("deleted successfully..");
   }

   @PreAuthorize("hasAnyRole('ADMIN','USER')")
   @PutMapping("/update/{postId}")
   public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                             @PathVariable Integer postId)
   {
        PostDto postDto1 = this.postService.updatePost(postDto,postId);
        return ResponseEntity.ok().body(postDto1);
   }

   @PreAuthorize("hasAnyRole('ADMIN','USER')")
   @GetMapping("/getPostByPostId/{postId}")
   public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer postId)
   {
       PostDto postDto = this.postService.getPostById(postId);
       return ResponseEntity.ok(postDto);
   }

   ///get by user
   @PreAuthorize("hasAnyRole('ADMIN','USER')")
   @GetMapping("/postByUser/{userId}")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
    {

        List<PostDto> postDto = this.postService.getPostByUser(userId);
        return ResponseEntity.ok().body(postDto);

    }

    ///get by category
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/postByCategory/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
    {
        List<PostDto> posts = this.postService.getPostByCategory(categoryId);
        return ResponseEntity.ok().body(posts);
    }


    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keyword)
    {
        List<PostDto> posts = this.postService.searchPost(keyword);
        return ResponseEntity.ok().body(posts);
    }

}
