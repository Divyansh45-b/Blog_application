package com.divyanshCode.BlogApplication.helper;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {

    private List<CategoryDto> categoryContent;

    private int pageNumber;
    private Long totalCategory;
    private int pageSize;
    private boolean isLastPage;

}
