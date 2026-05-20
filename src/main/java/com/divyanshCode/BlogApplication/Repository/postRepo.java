package com.divyanshCode.BlogApplication.Repository;

import com.divyanshCode.BlogApplication.Entity.Category;
import com.divyanshCode.BlogApplication.Entity.Post;
import com.divyanshCode.BlogApplication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface postRepo extends JpaRepository<Post,Integer> {

    /// for simple queries we sue query method but for complex we use @Query annotation
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}
