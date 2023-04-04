package com.Nia.electronic.store.impl;


import com.Nia.electronic.store.UserService.UserService;
import com.Nia.electronic.store.controllers.UserController;
import com.Nia.electronic.store.dtos.PageableResponse;
import com.Nia.electronic.store.dtos.UserDto;
import com.Nia.electronic.store.entites.User;
import com.Nia.electronic.store.exceptions.ResourceNotFoundException;
import com.Nia.electronic.store.helper.Helper;
import com.Nia.electronic.store.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UserService {
@Autowired
private UserRepository userRepository;
    private static Logger logger = LoggerFactory.getLogger( UserRepository.class);
@Autowired
ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {

        //for generate unique id in String format
        String userId = UUID.randomUUID().toString();
        logger.info("Intiating the Dao call for create the user data with userId");
        userDto.setUserId(userId);
       User user = dtoToEntity(userDto);
        User savedUser = this.userRepository.save(user);
        UserDto newDto = entityToDto(savedUser);
        logger.info("Complete the dao call for create  the user data with userId ");
        return newDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        logger.info("Intiating the Dao call for update the user data with userId {}",userId);
       User user= userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User is not found with given id"));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());

      User updatedUser=  userRepository.save(user);
      UserDto updatedDto= entityToDto(updatedUser);
        logger.info("complete the dao call for update the user data with userId {} :",userId);
        return updatedDto;
    }

    @Override
    public void deleteUser(String userId) {
        logger.info("Intiating the dao call for the delete user data with userId");
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found  with given id"));
        logger.info("Complete the Dao call for the deleteuser with userId");
        userRepository.delete(user);
    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber , int pageSize, String sortby, String sortDir) {
       Sort sort = Sort.by(sortby);
        logger.info("Intiating the dao call for the get all user data");
        Pageable pageable  = PageRequest.of(pageNumber, pageSize ,sort);
        List<User> users =  userRepository.findAll();
        Page<User> page = userRepository.findAll(pageable);

        PageableResponse<UserDto> response = Helper.getPageableResponse(page, UserDto.class);
         logger.info("Complete the dao call for the get all user data");
        return response;
    }

    @Override
    public UserDto getUserById(String userId) {
        logger.info("Intiating the dao call for the get userById with  user data");
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with given id"));
        logger.info("Complete the dao call for the get userById data");
        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        logger.info("Intiating the Dao call for the getUserByEmail with the user data ");
        User user= userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User not found with given email id"));
        logger.info("Comlete the Dao call for the getUserByEmail the user the data");
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        logger.info("Intiating the Dao call for the searchUser with the user data");
        List<User> users=  userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList= users.stream().map(user ->entityToDto(user)).collect(Collectors.toList());
       logger.info("Complete thedao call for the searchUser with the user data");
        return dtoList;
    }


    private User dtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

        private UserDto entityToDto(User savedUser){
            return modelMapper.map(savedUser,UserDto.class);

    }
}
