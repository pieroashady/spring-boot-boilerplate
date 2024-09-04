package com.thexyde.hris.module.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thexyde.hris.entity.Session;
import com.thexyde.hris.exception.RefreshTokenException;
import com.thexyde.hris.module.session.SessionService;
import com.thexyde.hris.security.JwtService;

import org.springframework.security.core.AuthenticationException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private JwtService jwtService;

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

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshDTO refreshDTO) {

        return sessionService.findByToken(refreshDTO.getRefreshToken())
                .map(sessionService::verifyExpiration)
                .map(Session::getUser)
                .map(user -> {
                    String token = jwtService.generateRefreshToken(user.getUsername());
                    return ResponseEntity.ok(new RefreshResponse(token, refreshDTO.getRefreshToken()));
                })
                .orElseThrow(() -> new RefreshTokenException(refreshDTO.getRefreshToken(),
                        "Invalid refresh token"));
    }
}