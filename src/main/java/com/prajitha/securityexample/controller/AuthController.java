package com.prajitha.securityexample.controller;

import com.prajitha.securityexample.dtos.LoginRequest;
import com.prajitha.securityexample.service.AuthenticationService;
import com.prajitha.securityexample.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager,
                          AuthenticationService authenticationService,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        this.jwtUtil = jwtUtil;
    }
    // login endpoint for users
    @PostMapping("/login")
    public String getLoginPages(@RequestBody LoginRequest loginRequest){
        //create the user level token with credentials
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword());
        authenticationManager.authenticate(token); // send the token to authenticationManager for verification
        UserDetails user = authenticationService.loadUserByUsername(loginRequest.getUsername());
        return  jwtUtil.generateToken(user); // return the token with stamp
    }

    @GetMapping("loginpage")
    public String testLogin(){
        return "Login page works";
    }
}
