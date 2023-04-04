package com.Nia.electronic.store.controllers;


import com.Nia.electronic.store.UserService.FileService;
import com.Nia.electronic.store.UserService.UserService;
import com.Nia.electronic.store.dtos.ApiResponseMessage;
import com.Nia.electronic.store.dtos.ImageResponse;
import com.Nia.electronic.store.dtos.PageableResponse;
import com.Nia.electronic.store.dtos.UserDto;
import org.hibernate.engine.jdbc.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;



@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Value("${user.profile.image.path}")
    private String imageUploadPath;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    //created
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        logger.info(" Initiating a controller call for createUser");
        UserDto userDto1 = userService.createUser(userDto);
        logger.info(" Initiating a controller call for createUser");
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    //updated
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") String userId, @Valid @RequestBody UserDto userDto) {
        logger.info("Initiating a controller call for updateUser");
        UserDto updatedUserDto = userService.updateUser(userDto, userId);
        logger.info(" completed a controller call for UpdateUser");
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        logger.info(" Initiating a controller call for DeleteUser");

        ApiResponseMessage message = ApiResponseMessage.builder().message("User is deleted Successfully !!").success(true).status(HttpStatus.OK).build();
        logger.info(" completed a controller call for DeleteUser");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //get by all
    @GetMapping("/users")
    public ResponseEntity<PageableResponse<UserDto>> getAllUsers(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                                 @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
                                                                 @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        logger.info("Initiating a controller call for getAllUser ");
        PageableResponse<UserDto> allUser = userService.getAllUser(pageNumber, pageSize, sortBy, sortDir);
        logger.info(" completed a controller call for getAllUser");
        return new ResponseEntity<>(userService.getAllUser(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);


    }

    //get by single
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
        logger.info("Initiating a controller call for getUser ");
        UserDto userById = userService.getUserById(userId);
        logger.info("completed a controller call for getUser ");
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    //get by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        logger.info("Initiating a controller call for email User ");
        UserDto userByEmail = this.userService.getUserByEmail(email);
        logger.info("completed a controller call for email User ");
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    //search user
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords) {
        logger.info("Initiating a controller call for search User ");
        List<UserDto> userDtos = this.userService.searchUser(keywords);
        logger.info("completed a controller call for search User ");
        return new ResponseEntity<>(userService.searchUser(keywords), HttpStatus.OK);
    }

    //upload user image
    @PostMapping("/image/{userid}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("userImage") MultipartFile image, @PathVariable String userid) throws IOException {
        String imageName = fileService.uploadFile(image, imageUploadPath);
        UserDto user = userService.getUserById(userid);
        user.setImageName(imageName);
        UserDto userDto = userService.updateUser(user, userid);
        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
    }

    //serve user image
    @GetMapping("/image/{userid}")
    public void serveUserImage(@PathVariable String userId, HttpServletResponse response) throws IOException {
        UserDto user = userService.getUserById(userId);
        logger.info("User image name :{}", user.getImageName());
        InputStream resource = fileService.getResource(imageUploadPath, user.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}