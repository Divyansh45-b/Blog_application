package com.divyanshCode.BlogApplication.Service.impl;

import com.divyanshCode.BlogApplication.Entity.Category;

import com.divyanshCode.BlogApplication.Entity.Post;
import com.divyanshCode.BlogApplication.Entity.User;

import com.divyanshCode.BlogApplication.Exception.ResourceNotFound;
import com.divyanshCode.BlogApplication.Repository.categoryRepo;
import com.divyanshCode.BlogApplication.Repository.postRepo;
import com.divyanshCode.BlogApplication.Repository.userRepo;
import com.divyanshCode.BlogApplication.Service.postService;
import com.divyanshCode.BlogApplication.helper.PostDto;

import com.divyanshCode.BlogApplication.helper.PostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostImpl implements postService {

    @Autowired
    private postRepo postRepo;

    @Autowired
    private userRepo userRepo;

    @Autowired
    private categoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    /// create post
    @Override
    public PostDto createPost(PostDto postDto, Integer id, Integer category_id) {

     User user =  this.userRepo.findById(id)
                                   .orElseThrow(()->new ResourceNotFound("User not found with this id "));
     Category category = this.categoryRepo
                                  .findById(category_id).orElseThrow(()-> new ResourceNotFound("Category not found with this id"));

        Post post = modelMapper.map(postDto, Post.class);///convert To Post

        post.setAddDate(new Date());
        post.setImageName("default.png");
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost, PostDto.class);
    }

    /// update post
    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFound(" post not found with id : "+postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post newPost = this.postRepo.save(post);

        return  this.modelMapper.map(newPost, PostDto.class);
    }

    /// delete post
    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFound("post not found with this id :"+postId));
        this.postRepo.delete(post);
    }

    /// getAll post
    @Override
    public PostResponse getAllPost(int pageNum, int pageSize, String sortBy) {


          Pageable p = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));

          Page<Post> postPage = this.postRepo.findAll(p);///pageable object will send to repo and find the data.

          List<Post> list = postPage.getContent();

          /// convert to dto using for each or stream
          List<PostDto> newList = new ArrayList<>();

          for(Post post:list)
          {
              newList.add(this.modelMapper.map(post, PostDto.class));
          }
          /// setting postResponse here.
          PostResponse postResponse = new PostResponse();
          postResponse.setContent(newList);
          postResponse.setPageSize(postPage.getSize());
          postResponse.setTotalElements(postPage.getTotalElements());
          postResponse.setPageNumber(postPage.getNumber());
          postResponse.setLastPage(postPage.isLast());

          return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFound("Post not found with id: " + postId));


         return this.modelMapper.map(post, PostDto.class);
    }

    ///getPostByCategory
    @Override
    public Page<PostDto> getPostByCategory(Integer categoryId, int page, int size) {

       Category category = this.categoryRepo.findById(categoryId)
               .orElseThrow(()-> new ResourceNotFound("category not found with id : "+categoryId));

       Pageable pageable = PageRequest.of(page, size);

       Page<Post> posts = this.postRepo.findByCategory(category, pageable);

        return posts.map(p -> this.modelMapper.map(p, PostDto.class));
    }

    ///getPostByUser
    @Override
    public Page<PostDto> getPostByUser(Integer userId, int page, int size) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFound("user not found with id : " + userId));

        Pageable pageable = PageRequest.of(page, size);

        Page<Post> posts = this.postRepo.findByUser(user, pageable);

        return posts.map(p -> this.modelMapper.map(p, PostDto.class));
    }

    @Override
    public List<PostDto> searchPost(String keyword) {

        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        List<PostDto> newPost  = new ArrayList<>();
        for(Post p: posts)
        {
            newPost.add(this.modelMapper.map(p,PostDto.class));
        }
        return newPost;
    }


}
