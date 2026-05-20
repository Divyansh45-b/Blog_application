package com.divyanshCode.BlogApplication.Controller;

import com.divyanshCode.BlogApplication.Service.categoryService;
import com.divyanshCode.BlogApplication.helper.CategoryDto;
import com.divyanshCode.BlogApplication.helper.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/category/")
public class CategoryController {

    @Autowired
    categoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
    {
        CategoryDto categoryDto1 = this.categoryService.createCategory(categoryDto);
        return ResponseEntity.status(201).body(categoryDto1);
    }
    // GET all categories
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/allCategory")
    public ResponseEntity<CategoryResponse> showCategory(@RequestParam(defaultValue = "0") int pageNum,@RequestParam (defaultValue = "6") int pageSize) {
        return ResponseEntity.ok(this.categoryService.showCategory(pageNum, pageSize));
    }

    // GET single Category
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/singleCategory/{id}")
    public ResponseEntity<CategoryDto> singleCategory(@PathVariable Integer id) {
        return ResponseEntity.ok(this.categoryService.singleCategory(id));
    }

    // PUT update Category
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> updateCategory (@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
        return ResponseEntity.ok(this.categoryService.updateCategory(categoryDto, id));
    }

    // DELETE Category
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        this.categoryService.deleteCategoryById(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

}
