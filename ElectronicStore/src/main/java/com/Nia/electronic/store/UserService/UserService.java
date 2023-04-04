package com.Nia.electronic.store.UserService;

import com.Nia.electronic.store.controllers.UserController;
import com.Nia.electronic.store.dtos.PageableResponse;
import com.Nia.electronic.store.dtos.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public interface UserService {

    public static Logger logger = LoggerFactory.getLogger( UserController.class);
    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto,String userId);

    //delete
    void deleteUser(String UserId);

    //get all users
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);


    //get single user by id
    UserDto getUserById(String userId);

    UserDto getUserByEmail(String email);

    //search user
    List<UserDto>searchUser(String keyword);

}
