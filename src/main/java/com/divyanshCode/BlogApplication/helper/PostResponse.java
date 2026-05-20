package com.divyanshCode.BlogApplication.helper;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {

    private List<PostDto> content;

    private int pageNumber;

    private Long totalElements;

    private int pageSize;

    private boolean isLastPage;

}
