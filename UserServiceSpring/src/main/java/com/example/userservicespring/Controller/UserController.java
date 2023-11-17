package com.example.userservicespring.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/data")
    public String user(){
        return "hello";
    }
}
