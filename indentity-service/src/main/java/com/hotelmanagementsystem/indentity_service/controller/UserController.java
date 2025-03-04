package com.hotelmanagementsystem.indentity_service.controller;

import com.hotelmanagementsystem.indentity_service.dto.request.UserCreationRequest;
import com.hotelmanagementsystem.indentity_service.entity.User;
import com.hotelmanagementsystem.indentity_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    User createUser(@RequestBody UserCreationRequest request) {
       return userService.createUser(request);
    }

    @GetMapping
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
