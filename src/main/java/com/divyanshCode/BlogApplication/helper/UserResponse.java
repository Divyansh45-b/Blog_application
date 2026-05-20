package com.divyanshCode.BlogApplication.helper;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private List<UserDto> userContent;

    private int pageNumber;
    private Long totalUser;
    private int pageSize;
    private boolean isLastPage;

}
