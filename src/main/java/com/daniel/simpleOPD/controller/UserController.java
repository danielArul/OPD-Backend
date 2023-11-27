package com.daniel.simpleOPD.controller;

import com.daniel.simpleOPD.dao.UserRepo;
import com.daniel.simpleOPD.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/addUser")
    public ResponseEntity<User> addDoctor(@RequestBody User user){
        System.out.println(user);
        user.setPassword(encoder.encode(user.getPassword()));
        User userObj= userRepo.save(user);
        return new ResponseEntity<>(userObj, HttpStatus.OK);

    }
}
