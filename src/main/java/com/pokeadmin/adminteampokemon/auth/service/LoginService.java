package com.pokeadmin.adminteampokemon.auth.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pokeadmin.adminteampokemon.auth.dto.LoginRequest;
import com.pokeadmin.adminteampokemon.auth.dto.LoginResponse;
import com.pokeadmin.adminteampokemon.common.exception.InvalidCredentialsException;
import com.pokeadmin.adminteampokemon.config.JwtProperties;
import com.pokeadmin.adminteampokemon.security.JwtService;
import com.pokeadmin.adminteampokemon.trainer.entity.TrainerEntity;
import com.pokeadmin.adminteampokemon.trainer.repository.TrainerRepository;

@Service
public class LoginService {
    private final TrainerRepository trainerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;
    
    public LoginService(TrainerRepository trainerRepository, PasswordEncoder passwordEncoder, JwtService jwtService, JwtProperties jwtProperties) {
        this.trainerRepository = trainerRepository;
        this.passwordEncoder= passwordEncoder;
        this.jwtService = jwtService;
        this.jwtProperties = jwtProperties;
    }

    public LoginResponse login(LoginRequest request) {

        // validate the request
        Optional <TrainerEntity> trainer = trainerRepository.findByUsername(request.getUsername());
        if (!trainer.isPresent() || !passwordEncoder.matches(request.getPassword(), trainer.get().getPassword())) {
            throw new InvalidCredentialsException();
        }
        String token = jwtService.generateToken(trainer.get());

        return new LoginResponse(token, jwtProperties.getExpiration());

    }



}
