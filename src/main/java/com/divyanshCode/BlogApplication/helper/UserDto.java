package com.divyanshCode.BlogApplication.helper;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class UserDto {

    private int userId;

    @NotEmpty
    @Size(min=4, max = 10,message = "name can not be empty!!")
    private String name;

    @NotEmpty(message = "email can not be empty")
    @Email(message = "enter a valid email!!")
    private String email;

    @NotEmpty
    @Size(min=5,message = "write something in about")
    private String about;

    @NotEmpty
    @Size(min=4,max =15,message ="password can not be empty")
    private String password;

}

///using dto -data transfer object with the help of this we can send back the essential details
/// to the client hiding the important details.