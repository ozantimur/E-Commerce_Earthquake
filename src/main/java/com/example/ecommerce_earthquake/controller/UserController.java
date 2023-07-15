package com.example.ecommerce_earthquake.controller;

import com.example.ecommerce_earthquake.model.User;
import com.example.ecommerce_earthquake.model.dto.LoginRequest;
import com.example.ecommerce_earthquake.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public List<User> getAllUsers() {

        try {

            List<User> users = userService.findAll();
            return users;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @PostMapping("/sing-up")
    public ResponseEntity<Boolean> createUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            return new ResponseEntity<String>("User logged in successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Invalid email or password.", HttpStatus.UNAUTHORIZED);
        }
    }

}