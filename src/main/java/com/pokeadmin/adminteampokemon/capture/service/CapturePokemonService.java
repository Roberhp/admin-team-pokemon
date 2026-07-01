package com.pokeadmin.adminteampokemon.capture.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pokeadmin.adminteampokemon.capture.dto.CapturePokemonRequest;
import com.pokeadmin.adminteampokemon.capture.dto.CapturePokemonResponse;
import com.pokeadmin.adminteampokemon.capture.entity.CapturedPokemonEntity;
import com.pokeadmin.adminteampokemon.capture.entity.PokemonLocation;
import com.pokeadmin.adminteampokemon.capture.repository.CapturePokemonRepository;
import com.pokeadmin.adminteampokemon.common.exception.PokemonAlreadyExistsException;
import com.pokeadmin.adminteampokemon.common.exception.PokemonStorageFullException;
import com.pokeadmin.adminteampokemon.common.exception.TrainerNotFoundException;
import com.pokeadmin.adminteampokemon.integration.pokemon.client.PokemonClient;
import com.pokeadmin.adminteampokemon.integration.pokemon.model.PokemonInfo;
import com.pokeadmin.adminteampokemon.trainer.entity.TrainerEntity;
import com.pokeadmin.adminteampokemon.trainer.repository.TrainerRepository;


@Service
public class CapturePokemonService {

    private static final int TEAM_LIMIT = 6;
    private static final int STORAGE_LIMIT = 30;
    
    private final CapturePokemonRepository capturePokemonRepository;
    private final TrainerRepository trainerRepository;
    private final PokemonClient pokemonClient;

    public CapturePokemonService(CapturePokemonRepository capturePokemonRepository, TrainerRepository trainerRepository, PokemonClient pokemonClient) {
        this.capturePokemonRepository = capturePokemonRepository;
        this.trainerRepository = trainerRepository;
        this.pokemonClient = pokemonClient;
    }
    
    public CapturePokemonResponse capturePokemon(CapturePokemonRequest request, Long trainerId) {
        
        PokemonInfo pokemonInfo = pokemonClient.findPokemonByPokedexNumber( request.getPokedexNumber() );

        TrainerEntity trainer = trainerRepository.findById(trainerId).orElseThrow(TrainerNotFoundException::new);

        CapturedPokemonEntity capturedPokemon = new CapturedPokemonEntity();
        capturedPokemon.setCapturedAt(LocalDateTime.now());
        capturedPokemon.setPokedexNumber(request.getPokedexNumber());

        capturedPokemon.setLocation(determineLocation(trainerId));


        String nickname = (request.getNickname() == null || request.getNickname().isBlank()) 
                        ? pokemonInfo.getNombre()
                        : request.getNickname();
        

        if (capturePokemonRepository.existsByTrainerTrainerIdAndNicknameAndPokedexNumber(
                trainerId,
                nickname,
                request.getPokedexNumber())) {
            throw new PokemonAlreadyExistsException();
        }

        capturedPokemon.setNickname(nickname);
        capturedPokemon.setTrainer(trainer);
        capturedPokemon.setSpriteUrl(pokemonInfo.getImageUrl());
        capturedPokemon = capturePokemonRepository.save(capturedPokemon);
        return new CapturePokemonResponse(capturedPokemon.getNickname(), capturedPokemon.getLocation().name());
    }

    private PokemonLocation determineLocation(Long trainerId) {
        long teamTotal = capturePokemonRepository.countByTrainerTrainerIdAndLocation(trainerId, PokemonLocation.TEAM);
        long storageTotal = capturePokemonRepository.countByTrainerTrainerIdAndLocation(trainerId, PokemonLocation.STORAGE);
        
        if (teamTotal >= TEAM_LIMIT && storageTotal >= STORAGE_LIMIT) {
            throw new PokemonStorageFullException();
        }
        
        if (teamTotal >= TEAM_LIMIT) {
            return PokemonLocation.STORAGE;
        }

        return PokemonLocation.TEAM;
    }
}
