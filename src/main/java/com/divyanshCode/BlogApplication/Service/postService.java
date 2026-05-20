package com.divyanshCode.BlogApplication.Service;



import com.divyanshCode.BlogApplication.helper.PostDto;
import com.divyanshCode.BlogApplication.helper.PostResponse;

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
    List<PostDto> getPostByCategory(Integer categoryId);


    /// getPostByUser
    List<PostDto> getPostByUser(Integer userId);


    /// search by keyword
    List<PostDto> searchPost (String keyword);
}
