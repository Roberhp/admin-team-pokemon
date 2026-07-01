package com.pokeadmin.adminteampokemon.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokeadmin.adminteampokemon.auth.dto.LoginRequest;
import com.pokeadmin.adminteampokemon.auth.dto.LoginResponse;
import com.pokeadmin.adminteampokemon.auth.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping ("/api/v1/auth")
public class AuthController {
    
    private final LoginService loginService;

    public AuthController(LoginService loginService)  {
        this.loginService = loginService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest request){
        LoginResponse response = loginService.login(request);
        return ResponseEntity.ok(response);
    }
}
