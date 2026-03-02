package com.ecommerce.ecomApplication.controller;

import com.ecommerce.ecomApplication.dtos.UserReq;
import com.ecommerce.ecomApplication.dtos.UserRes;
import com.ecommerce.ecomApplication.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceimpl;


    @GetMapping("/api/users")
    public ResponseEntity<List<UserRes>> findAllUser() {
        return new ResponseEntity<>(userServiceimpl.fetchUser(), HttpStatus.OK);

    }

    @PostMapping("/api/users")
   // @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody UserReq req) {
        System.out.println(req);
        String s = userServiceimpl.addUser(req);

        return ResponseEntity.ok(s);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> findUser(@PathVariable Long id) {
        Optional<UserRes> user = userServiceimpl.findUser(id);
        System.out.println(id);
        if (user != null) return ResponseEntity.ok(user);
        else return ResponseEntity.notFound().build();

    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserReq user) {

    Optional<UserRes> userFind = userServiceimpl.findUser(id);

        if (userFind == null) {
            return ResponseEntity.notFound().build();
        } else {
           Boolean b = userServiceimpl.updateUser(id,user);
            return ResponseEntity.ok(b);
        }
    }


}

