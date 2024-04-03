package com.csye6220.finalProject.controller;

import com.csye6220.finalProject.dao.UserDAO;
import com.csye6220.finalProject.model.User;
import com.csye6220.finalProject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);

    }

    @GetMapping("/allusers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/userbyid")
    public User getUserById(long id){
        return userService.getUserById(id);
    }


}
