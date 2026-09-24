package com.divyanshCode.BlogApplication.Service;



import com.divyanshCode.BlogApplication.helper.PostDto;
import com.divyanshCode.BlogApplication.helper.PostResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface postService {

    ///create Post
    PostDto createPost(PostDto postDto, Integer id, Integer category_id);

    /// update Post
    PostDto updatePost(PostDto postDto, Integer postId);

    /// delete Post
    void deletePost(Integer postId);

    /// getAllPost
   PostResponse getAllPost(int pageNum, int pageSize, String sortBy);

    /// getPostById
    PostDto getPostById(Integer postId);


    /// getPostByCategory
    Page<PostDto> getPostByCategory(Integer categoryId, int page, int size);


    /// getPostByUser
    Page<PostDto> getPostByUser(Integer userId, int page, int size);


    /// search by keyword
    List<PostDto> searchPost (String keyword);

}
