package com.divyanshCode.BlogApplication.Service.impl;

import com.divyanshCode.BlogApplication.Entity.Category;
import com.divyanshCode.BlogApplication.Exception.ResourceNotFound;
import com.divyanshCode.BlogApplication.Repository.categoryRepo;
import com.divyanshCode.BlogApplication.Service.categoryService;
import com.divyanshCode.BlogApplication.helper.CategoryDto;
import com.divyanshCode.BlogApplication.helper.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryImpl implements categoryService {

    @Autowired
    private categoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category1 = this.dtoToCategory(categoryDto);
        Category savedCategory = this.categoryRepo.save(category1);
        return this.categoryToDto(savedCategory);
    }

    @Override
    public CategoryResponse showCategory(int pageNum, int pageSize) {

        Pageable p = PageRequest.of(pageNum, pageSize);
        Page<Category> categoryPage = this.categoryRepo.findAll(p);

        List<Category> categoryList = categoryPage.getContent();

       List<CategoryDto> categoryDtoList = new ArrayList<>();
       for(Category c : categoryList)
       {
           categoryDtoList.add(this.categoryToDto(c));
       }

       /// setting category Response.
       CategoryResponse categoryResponse = new CategoryResponse();
       categoryResponse.setCategoryContent(categoryDtoList);
       categoryResponse.setPageNumber(categoryPage.getNumber());
       categoryResponse.setPageSize(categoryPage.getSize());
       categoryResponse.setTotalCategory(categoryPage.getTotalElements());
       categoryResponse.setLastPage(categoryPage.isLast());

       return categoryResponse;
    }

    @Override
    public CategoryDto singleCategory(Integer category_id) {

        Category category = this.categoryRepo.findById(category_id).orElseThrow(()->new ResourceNotFound("Category not found with id: "+category_id));
        return this.categoryToDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer category_id) {

        Category category = this.categoryRepo.findById(category_id).orElseThrow(()-> new ResourceNotFound("Category not found with id :"+category_id));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = this.categoryRepo.save(category);
        return this.categoryToDto(updatedCategory);

    }

    @Override
    public void deleteCategoryById(Integer category_id) {

         Category category = this.categoryRepo.findById(category_id).orElseThrow(()->new ResourceNotFound("Category of id : "+category_id+ " is not present"));
         this.categoryRepo.delete(category);

    }

    /// Conversion
    /// Category To CategoryDto
    public CategoryDto categoryToDto(Category category)
    {

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryTitle(category.getCategoryTitle());
        categoryDto.setCategoryDescription(category.getCategoryDescription());

        return categoryDto;
    }
    /// CategoryDto To Category
    public Category dtoToCategory(CategoryDto categoryDto)
    {
        Category category = new Category();
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        return category;
    }
}
