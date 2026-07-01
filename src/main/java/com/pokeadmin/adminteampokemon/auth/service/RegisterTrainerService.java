package com.pokeadmin.adminteampokemon.auth.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pokeadmin.adminteampokemon.auth.dto.RegisterTrainerRequest;
import com.pokeadmin.adminteampokemon.auth.dto.RegisterTrainerResponse;
import com.pokeadmin.adminteampokemon.common.exception.TrainerAlreadyExistsException;
import com.pokeadmin.adminteampokemon.trainer.entity.TrainerEntity;
import com.pokeadmin.adminteampokemon.trainer.repository.TrainerRepository;

@Service
public class RegisterTrainerService {

    private final TrainerRepository trainerRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterTrainerService(TrainerRepository trainerRepository, PasswordEncoder passwordEncoder) {
        this.trainerRepository = trainerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterTrainerResponse registerTrainer(RegisterTrainerRequest request) {

        if (trainerRepository.existsByUsername(request.getUsername())){
            throw new TrainerAlreadyExistsException(request.getUsername());
        }

        String passwordHash = passwordEncoder.encode(request.getPassword());

        TrainerEntity newTrainer = new TrainerEntity();
        newTrainer.setUsername(request.getUsername());
        newTrainer.setPassword(passwordHash);
        newTrainer.setTrainerName(request.getTrainerName());
        newTrainer.setOriginTown(request.getOriginTown());
        newTrainer.setRegisteredAt(LocalDateTime.now());
        TrainerEntity savedTrainer = trainerRepository.save(newTrainer);

        return new RegisterTrainerResponse(savedTrainer.getTrainerId());
    }
}
