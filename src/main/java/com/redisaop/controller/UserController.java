package com.redisaop.controller;

import com.redisaop.model.User;
import com.redisaop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usercontroller")
public class UserController {
    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/getall")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/getbyemail")
    public User getByEmail(@RequestParam String email){
        return userService.getByEmail(email);
    }


    @GetMapping("/getbyid")
    public User getById(@RequestParam int id){

        return userService.getById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> Add(@RequestBody User user){

        return ResponseEntity.ok(userService.Add(user));
    }

    @DeleteMapping("/delete")
    public void Delete(@RequestParam int id){
        userService.Delete(id);
    }

    @PutMapping("update")
    public void Update(@RequestParam int id,@RequestBody User user){

        userService.Update(id,user);
    }
}
