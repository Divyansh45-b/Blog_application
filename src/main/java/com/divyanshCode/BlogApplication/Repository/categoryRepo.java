package com.divyanshCode.BlogApplication.Repository;

import com.divyanshCode.BlogApplication.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryRepo extends JpaRepository<Category,Integer> {

}
