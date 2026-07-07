package com.pokeadmin.adminteampokemon.pokemon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokeadmin.adminteampokemon.capture.entity.PokemonLocation;
import com.pokeadmin.adminteampokemon.pokemon.dto.PokemonInfoResponse;
import com.pokeadmin.adminteampokemon.pokemon.dto.PokemonSummaryResponse;
import com.pokeadmin.adminteampokemon.pokemon.service.PokemonQueryService;
import com.pokeadmin.adminteampokemon.security.AuthenticatedTrainer;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/pokemon")
public class PokemonQueryController {
    private final PokemonQueryService pokemonQueryService;

    public PokemonQueryController(PokemonQueryService pokemonQueryService) {
        this.pokemonQueryService = pokemonQueryService;
    }

    @GetMapping("/team")
    public ResponseEntity<Page<PokemonInfoResponse>> getTeamPokemon(Authentication authentication, Pageable pageable) {
        AuthenticatedTrainer trainer = (AuthenticatedTrainer) authentication.getPrincipal();

        Page<PokemonInfoResponse> pokemonTeam = pokemonQueryService.findPokemonByLocation(trainer.getId(), PokemonLocation.TEAM, pageable);
        return ResponseEntity.ok(pokemonTeam);
    }

    @GetMapping("/storage")
    public ResponseEntity<Page<PokemonInfoResponse>> getStoragePokemon(
            Authentication authentication, 
            @PageableDefault(
                size = 10,
                sort = "nickname") Pageable pageable) {
        AuthenticatedTrainer trainer = (AuthenticatedTrainer) authentication.getPrincipal();

        Page<PokemonInfoResponse> storedPokemon = pokemonQueryService.findPokemonByLocation(trainer.getId(), PokemonLocation.STORAGE, pageable);
        return ResponseEntity.ok(storedPokemon);
    }

    @GetMapping("/summary")
    public ResponseEntity<PokemonSummaryResponse> getPokemonSummary(Authentication authentication) {
        AuthenticatedTrainer trainer = (AuthenticatedTrainer) authentication.getPrincipal();

        PokemonSummaryResponse pokemonSummary = pokemonQueryService.getTrainerPokemonSummary(trainer.getId());
        return ResponseEntity.ok(pokemonSummary);
    }
    
}
