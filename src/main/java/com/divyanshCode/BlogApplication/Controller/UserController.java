package com.divyanshCode.BlogApplication.Controller;


import com.divyanshCode.BlogApplication.Service.userService;
import com.divyanshCode.BlogApplication.helper.UserDto;
import com.divyanshCode.BlogApplication.helper.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private userService userService;

    /// permit All
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto userCreated = this.userService.Register(userDto);
        return ResponseEntity.status(201).body(userCreated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/showUserList")
    public ResponseEntity<UserResponse> getAllUser(@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "6") int pageSize)
    {
        UserResponse allUser = this.userService.getAllUser(pageNum, pageSize);
        return ResponseEntity.status(200).body(allUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{user_id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer user_id)
    {
        UserDto updatedUser = this.userService.update(userDto , user_id);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer user_id)
    {
        this.userService.deleteUserById(user_id);
        return ResponseEntity.ok("deleted successfully");
    }

   @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/singleUser/{id}")
    public ResponseEntity<UserDto> getSingleUser(@Valid @PathVariable("id") Integer user_id)
    {
        UserDto getUser = this.userService.getUserById(user_id);
        return ResponseEntity.ok(getUser);
    }
}
