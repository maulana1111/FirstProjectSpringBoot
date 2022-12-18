package com.second_project.second_project.controller;

import com.second_project.second_project.model.User;
import com.second_project.second_project.repository.UserRepository;
import com.second_project.second_project.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<Object> findAllUser(@RequestParam(name = "name", defaultValue = "") String name)
    {
        try{
            List<User> users;
            if(StringUtils.hasText(name))
            {
                users = userRepository.findUserByName(name);
            }else{
                users = userRepository.findAll();
            }
            if(users.isEmpty())
            {
                return ResponseHandler.generateResponse("Data User Empty", HttpStatus.MULTI_STATUS, null);
            }
            return ResponseHandler.generateResponse("Success Retrived Data", HttpStatus.OK, users);
        }catch (Exception e)
        {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody User user)
    {
        try {
//            User newUser = new User();
//            newUser.setUser_id(UUID.randomUUID().toString());
//            newUser.set
            return ResponseHandler.generateResponse("Success Created User", HttpStatus.CREATED, userRepository.save(user));
        }catch (Exception e)
        {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping(path = "/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @RequestBody User user)
    {
        User userData = userRepository.findUserById(id);
        try{
            if(!ObjectUtils.isEmpty(userData))
            {
                return ResponseHandler.generateResponse("User Updated", HttpStatus.OK, userRepository.save(user));
            }else{
                return ResponseHandler.generateResponse("User Not Found", HttpStatus.NOT_FOUND, null);
            }
        }catch (Exception e)
        {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> userById(@PathVariable("id") Long id)
    {
        User user = userRepository.findUserById(id);
        try{
            if(ObjectUtils.isEmpty(user)){
                return ResponseHandler.generateResponse("Data Not Found", HttpStatus.MULTI_STATUS, null);
            }
            return  ResponseHandler.generateResponse("Success Retrived Data", HttpStatus.OK, user);
        }catch (Exception e)
        {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

}
