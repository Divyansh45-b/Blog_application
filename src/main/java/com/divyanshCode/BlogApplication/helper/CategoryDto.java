package com.divyanshCode.BlogApplication.helper;


import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CategoryDto {


    private int categoryId;

    @NotBlank(message = "title can not be blank")
    private String categoryTitle;

    @NotBlank(message = "description can not be blank")
    private String categoryDescription;

}
