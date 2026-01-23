package com.ecommerce.ecomApplication.controller;

import com.ecommerce.ecomApplication.model.User;
import com.ecommerce.ecomApplication.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;


@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceimpl;


    @GetMapping("/api/users")
    public ResponseEntity<List<User>> findAllUser() {
        return new ResponseEntity<>(userServiceimpl.fetchUser(), HttpStatus.OK);

    }

    // @PostMapping("/api/users")
    //this is  
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody User user) {

        String s = userServiceimpl.addUser(user);

        return ResponseEntity.ok(s);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> findUser(@PathVariable Long id) {
       Optional<User> user = userServiceimpl.findUser(id);
        System.out.println(id);
        if (user != null) return ResponseEntity.ok(user);
        else return ResponseEntity.notFound().build();

    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        System.out.println(user.toString());
    Optional<User> userFind = userServiceimpl.findUser(id);
        System.out.println(userFind);
        if (userFind == null) {
            return ResponseEntity.notFound().build();
        } else {
           Boolean b = userServiceimpl.updateUser(id,user);
            return ResponseEntity.ok(b);
        }
    }


}

