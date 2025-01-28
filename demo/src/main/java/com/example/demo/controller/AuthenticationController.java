package com.example.demo.controller;

import com.example.demo.models.User;
import com.example.demo.dtos.LoginUserDto;
import com.example.demo.dtos.RegisterUserDto;
import com.example.demo.response.LoginResponse;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    private static String jwtToken;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    protected static String getToken() {
        return jwtToken;
    }

    private static final Logger log = LoggerFactory.getLogger(ExerciseController.class);

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        logger.info("User registered: {}", registeredUser);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        // add userId to token
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userId", authenticatedUser.getId());

        jwtToken = jwtService.generateToken(extraClaims,authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        log.info("User logged in : {}", loginResponse.toString());

        return ResponseEntity.ok(loginResponse);
    }
}
