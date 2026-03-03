package com.BootCampTTN.JWT_Security_assignment.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin dashboard";
    }
}
