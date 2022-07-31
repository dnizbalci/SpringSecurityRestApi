package com.works.restControllers;

import com.works.entities.User;
import com.works.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserRestController {

    final UserService uService;
    public UserRestController(UserService uService) {
        this.uService = uService;
    }

    @PostMapping("/register")
    public ResponseEntity register (@Valid @RequestBody User user){
        return uService.register(user);
    }
    @GetMapping("/update")
    public ResponseEntity update (@Valid @RequestBody User user){
        return uService.updateUser(user);
    }
}
