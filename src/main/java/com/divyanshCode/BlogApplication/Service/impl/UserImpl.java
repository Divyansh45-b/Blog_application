package com.divyanshCode.BlogApplication.Service.impl;

import com.divyanshCode.BlogApplication.Entity.Role;
import com.divyanshCode.BlogApplication.Entity.User;
import com.divyanshCode.BlogApplication.Exception.ResourceNotFound;
import com.divyanshCode.BlogApplication.Repository.roleRepo;
import com.divyanshCode.BlogApplication.helper.UserDto;
import com.divyanshCode.BlogApplication.Repository.userRepo;
import com.divyanshCode.BlogApplication.Service.userService;
import com.divyanshCode.BlogApplication.helper.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserImpl implements userService {

    @Autowired
    private userRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private roleRepo roleRepo;

    @Override
    public UserDto Register(UserDto userDto) {

        User user = dtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        /// default role is assigned here.
        Role role = this.roleRepo.findByRoleName("ROLE_USER")
                          .orElseThrow(()->new ResourceNotFound("role not found."));

        user.setRoles(List.of(role));

        User savedUser = userRepo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDto update(UserDto userDto, Integer user_id) {

        User user = this.userRepo.findById(user_id)
                .orElseThrow(()-> new ResourceNotFound("User not found with id : "+ user_id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User updatedUser = this.userRepo.save(user);
        return this.userToDto(updatedUser);

    }

    @Override
    public UserDto getUserById(Integer user_id) {

        User user = this.userRepo.findById(user_id)
                .orElseThrow(()-> new ResourceNotFound("User not found with id : "+ user_id));

        return this.userToDto(user);

    }

    @Override
    public UserResponse getAllUser(int pageNum, int pageSize) {

        Pageable p = PageRequest.of(pageNum, pageSize);

        Page<User> userPage = this.userRepo.findAll(p);

        List<User> newList = userPage.getContent();

        List<UserDto> userDtoList = new ArrayList<>();

        for(User u : newList)
        {
            userDtoList.add(this.userToDto(u));
        }

        ///setting userResponse here.
        UserResponse userResponse = new UserResponse();
        userResponse.setUserContent(userDtoList);
        userResponse.setTotalUser(userPage.getTotalElements());
        userResponse.setPageSize(userPage.getSize());
        userResponse.setPageNumber(userPage.getNumber());
        userResponse.setLastPage(userPage.isLast());

       return userResponse;
    }

    @Override
    public void deleteUserById(Integer user_id) {

        User user = this.userRepo.findById(user_id)
                .orElseThrow(()->new ResourceNotFound("User not found with id : "+ user_id));

         this.userRepo.delete(user);
    }


    /// convert dto to User
    public User dtoToUser(UserDto userDto)
    {
        User user =  new User();

        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        return user;
    }

    /// convert User to Dto
    public UserDto userToDto(User user)
    {
        UserDto userDto = new UserDto();

        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());

        return userDto;
    }


}
