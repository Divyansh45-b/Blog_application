package com.divyanshCode.BlogApplication.Repository;

import com.divyanshCode.BlogApplication.Entity.Comment;

import com.divyanshCode.BlogApplication.Entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface commentRepo extends JpaRepository<Comment, Integer> {


     Page<Comment> findByPost(Post post, Pageable pageable);
}
