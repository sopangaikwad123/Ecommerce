package com.Nia.electronic.store.dtos;
import com.Nia.electronic.store.controllers.UserController;
import com.Nia.electronic.store.validate.ImageNameValid;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private static Logger logger = LoggerFactory.getLogger( UserController.class);
    private String userId;

    @Size(min = 3, max =20, message = "Invalid Name !!")
    private String name;
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$\",message = Invalid User Email !!")
    @Email(message = "Invalid User Email")
    private String email;
    @NotBlank(message = "Password is required !!")
    private String password;

    @Size(min= 4, max =6, message = "invalid gender !!")
    private String gender;

    @NotBlank(message = "Write something about  yourself!!")
    private String about;


    @ImageNameValid
    private String imageName;

}
