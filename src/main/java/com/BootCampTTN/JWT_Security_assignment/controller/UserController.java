package com.BootCampTTN.JWT_Security_assignment.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/home")
    public String home() {
        return "user home";
    }
}
