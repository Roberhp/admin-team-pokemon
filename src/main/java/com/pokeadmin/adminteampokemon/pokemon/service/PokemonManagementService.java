package com.pokeadmin.adminteampokemon.pokemon.service;


import org.springframework.stereotype.Service;

import com.pokeadmin.adminteampokemon.capture.entity.CapturedPokemonEntity;
import com.pokeadmin.adminteampokemon.capture.entity.PokemonLocation;
import com.pokeadmin.adminteampokemon.capture.repository.CapturePokemonRepository;
import com.pokeadmin.adminteampokemon.common.exception.PokemonAlreadyExistsException;
import com.pokeadmin.adminteampokemon.common.exception.PokemonNotFoundException;
import com.pokeadmin.adminteampokemon.common.exception.PokemonStorageFullException;
import com.pokeadmin.adminteampokemon.pokemon.dto.MovePokemonResponse;

@Service
public class PokemonManagementService {

    private static final int TEAM_LIMIT = 6;
    private static final int STORAGE_LIMIT = 30;

    
    private final CapturePokemonRepository capturePokemonRepository;

    public PokemonManagementService(CapturePokemonRepository capturePokemonRepository) {
        this.capturePokemonRepository = capturePokemonRepository;
    }   

    public void freePokemon(Long capturedPokemonId, Long trainerId) {
        if (capturePokemonRepository.existsByTrainerTrainerIdAndCapturedPokemonId(trainerId, capturedPokemonId)) {
            capturePokemonRepository.deleteByCapturedPokemonId(capturedPokemonId);
        } else {
            throw new PokemonNotFoundException();
        }
    }

    public void renamePokemon(Long capturedPokemonId, Long trainerId, String newName) {
        CapturedPokemonEntity pokemon = capturePokemonRepository
                                                    .findByCapturedPokemonId(capturedPokemonId)
                                                    .orElseThrow(PokemonNotFoundException::new);
        if (capturePokemonRepository.existsByTrainerTrainerIdAndNicknameAndPokedexNumber(
                trainerId,
                newName,
                pokemon.getPokedexNumber())) {
            throw new PokemonAlreadyExistsException();
        }
        
        pokemon.setNickname(newName);
        capturePokemonRepository.save(pokemon);
    }

    public MovePokemonResponse movePokemon(Long capturedPokemonId, Long trainerId, PokemonLocation newLocation) {
        CapturedPokemonEntity pokemon = capturePokemonRepository
                                                    .findByTrainerTrainerIdAndCapturedPokemonId(trainerId, capturedPokemonId)
                                                    .orElseThrow(PokemonNotFoundException::new);

        validateNewLocation(trainerId, newLocation);

        pokemon.setLocation(newLocation);
        capturePokemonRepository.save(pokemon);
        return new MovePokemonResponse(pokemon.getCapturedPokemonId(), pokemon.getLocation().name());

    }

    private void validateNewLocation(Long trainerId, PokemonLocation newLocation) {

        long totalPokemonNewLocation = capturePokemonRepository.countByTrainerTrainerIdAndLocation(trainerId, newLocation);
        switch (newLocation) {
            case TEAM:
                if (totalPokemonNewLocation >= TEAM_LIMIT) {
                    throw new PokemonStorageFullException();
                }
                break;
            case STORAGE:
                if (totalPokemonNewLocation >= STORAGE_LIMIT) {
                    throw new PokemonStorageFullException();
                }
                break;
        }

    }

}
