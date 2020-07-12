package com.junit.mockito.JUnit_Mockito_Tutorial.controller;


import com.junit.mockito.JUnit_Mockito_Tutorial.model.User;
import com.junit.mockito.JUnit_Mockito_Tutorial.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> getAllUsers () {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable int id) {
       return service.getUserByID(id);

    }

    @PostMapping("/add")
    public String addUser (User user ){
        service.addUser(user);
        return "Added Successfully";
    }

    @DeleteMapping("/del/{id}")
    public String deleteUser (@RequestBody User user) {
        service.deleteUser(user);
        return "Deleted Successfully";
    }
}
