package com.divyanshCode.BlogApplication.Service;

import com.divyanshCode.BlogApplication.helper.CategoryDto;
import com.divyanshCode.BlogApplication.helper.CategoryResponse;



public interface categoryService {

    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //read
    CategoryResponse showCategory(int pageNum, int pageSize);

    //readById
    CategoryDto singleCategory(Integer category_id);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer category_id);

    //delete
    void deleteCategoryById(Integer category_id);
}
