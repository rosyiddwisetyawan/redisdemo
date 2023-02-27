package com.api.redisdemo.controller;

import com.api.redisdemo.model.User;
import com.api.redisdemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "user", key = "#userId")
    @GetMapping("/get/{userId}")
    public String getUser(@PathVariable String userId) {
        User user = userRepository.getById(Integer.parseInt(userId));
        LOG.info("get");
        return user.getName();
    }

    @GetMapping("/getdata/{userId}")
    public String getData(@PathVariable String userId) {
        User user = userRepository.getById(Integer.parseInt(userId));
        LOG.info("getdata");
        return user.getName();
    }

    @PostMapping("/add/{name}/{email}")
    public String addUser(@PathVariable String name, @PathVariable String email){
        User us = new User();
        us.setEmail(email);
        us.setName(name);
        userRepository.save(us);
        return "success";
    }
}
