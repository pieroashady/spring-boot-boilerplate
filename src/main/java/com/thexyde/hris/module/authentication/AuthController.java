package com.thexyde.hris.module.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.AuthenticationException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthDTO loginDTO) {
        try {
            AuthResponse loginResponse = authService.login(loginDTO);
            return ResponseEntity.ok(loginResponse);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Invalid login credentials");
        }
    }
}