package com.BootCampTTN.JWT_Security_assignment.controller;


import com.BootCampTTN.JWT_Security_assignment.config.JwtUtil;
import com.BootCampTTN.JWT_Security_assignment.entity.User;
import com.BootCampTTN.JWT_Security_assignment.repo.UserRepo;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepo userRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

       userRepository.save(user);

        return "registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()));

        User dbUser = (User) userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return jwtUtil.generateToken(
                dbUser.getUsername(),
                dbUser.getRole());
    }
}