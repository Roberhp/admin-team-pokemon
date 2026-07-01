package com.pokeadmin.adminteampokemon.pokemon.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pokeadmin.adminteampokemon.capture.entity.CapturedPokemonEntity;
import com.pokeadmin.adminteampokemon.capture.entity.PokemonLocation;
import com.pokeadmin.adminteampokemon.capture.repository.CapturePokemonRepository;
import com.pokeadmin.adminteampokemon.pokemon.dto.PokemonInfoResponse;
import com.pokeadmin.adminteampokemon.pokemon.dto.PokemonSummaryResponse;

@Service
public class PokemonSearchService {
    
    private final CapturePokemonRepository  capturePokemonRepository ;

    public PokemonSearchService(CapturePokemonRepository  capturePokemonRepository){
        this.capturePokemonRepository = capturePokemonRepository;
    }

    // Consultar Equipo
    public List<PokemonInfoResponse> findPokemonByLocation(Long idTrainer, PokemonLocation location){
        Optional<CapturedPokemonEntity>  capturedPokemon = capturePokemonRepository.findByTrainerTrainerIdAndCapturedPokemonId(idTrainer, idTrainer);
        
        return capturedPokemon.stream()
                .map(PokemonInfoResponse::new)
                .toList();
        
    }

    public PokemonSummaryResponse getTrainerPokemonSummary(Long IdTrainer){
        Long totalTeam = capturePokemonRepository.countByTrainerTrainerIdAndLocation(IdTrainer, PokemonLocation.TEAM);
        Long totalBox = capturePokemonRepository.countByTrainerTrainerIdAndLocation(IdTrainer, PokemonLocation.STORAGE);
        return new PokemonSummaryResponse(totalTeam, totalBox);
    }


}
