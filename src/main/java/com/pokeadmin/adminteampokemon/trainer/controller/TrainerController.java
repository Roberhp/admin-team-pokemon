package com.pokeadmin.adminteampokemon.trainer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokeadmin.adminteampokemon.auth.dto.RegisterTrainerRequest;
import com.pokeadmin.adminteampokemon.auth.dto.RegisterTrainerResponse;
import com.pokeadmin.adminteampokemon.auth.service.RegisterTrainerService;



@RestController
@RequestMapping("/api/v1/auth")
public class TrainerController {
    
    private final RegisterTrainerService trainerService;

    public TrainerController(RegisterTrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterTrainerResponse> registerTrainer(@RequestBody RegisterTrainerRequest request) {
        RegisterTrainerResponse response =  trainerService.registerTrainer(request);
        
        return ResponseEntity.status(201).body(response);
    }
}
