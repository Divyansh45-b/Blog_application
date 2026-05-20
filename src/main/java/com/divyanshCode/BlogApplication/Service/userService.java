package com.divyanshCode.BlogApplication.Service;

import com.divyanshCode.BlogApplication.helper.UserDto;
import com.divyanshCode.BlogApplication.helper.UserResponse;



public interface userService {

      UserDto Register(UserDto userDto);

      UserDto update(UserDto userDto, Integer user_id);

      UserDto getUserById(Integer user_id);

      UserResponse getAllUser(int pageNum, int pageSize);

      void deleteUserById(Integer user_id);


}
