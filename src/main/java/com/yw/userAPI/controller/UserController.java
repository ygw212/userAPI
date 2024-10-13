package com.yw.userAPI.controller;

import com.yw.userAPI.controller.request.UpdateRequest;
import com.yw.userAPI.controller.request.UserRequest;
import com.yw.userAPI.dto.UserDTO;
import com.yw.userAPI.model.User;
import com.yw.userAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //save a user
    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserRequest user){
        User savedUser = userService.saveUser(mapToModel(user));
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //get all users
    //to do: implement pagination if the number of users is too large
    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    //get a user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String id){
        try{
            User user = userService.getUser(id);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //update a user
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @Valid @RequestBody UpdateRequest user){
        try{
            User updatedUser = userService.updateUser(id,mapToModel(user));
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //delete a user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") String id){
        try{
            User deletedUser = userService.deleteUser(id);
            return new ResponseEntity<>(deletedUser,HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private UserDTO mapToModel(UserRequest request){
        UserDTO userDTO = UserDTO.builder()
                .userName(request.getUserName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();
        return userDTO;
    }

    private UserDTO mapToModel(UpdateRequest request){
        UserDTO userDTO = UserDTO.builder()
                .userName(request.getUserName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();
        return userDTO;
    }
}
