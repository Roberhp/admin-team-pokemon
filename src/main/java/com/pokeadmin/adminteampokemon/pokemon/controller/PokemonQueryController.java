package com.pokeadmin.adminteampokemon.pokemon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokeadmin.adminteampokemon.capture.entity.PokemonLocation;
import com.pokeadmin.adminteampokemon.pokemon.dto.PokemonInfoResponse;
import com.pokeadmin.adminteampokemon.pokemon.dto.PokemonSummaryResponse;
import com.pokeadmin.adminteampokemon.pokemon.service.PokemonQueryService;
import com.pokeadmin.adminteampokemon.security.AuthenticatedTrainer;

import java.util.List;

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
    public ResponseEntity<List<PokemonInfoResponse>> getTeamPokemon(Authentication authentication) {
        AuthenticatedTrainer trainer = (AuthenticatedTrainer) authentication.getPrincipal();

        List<PokemonInfoResponse> pokemonTeam = pokemonQueryService.findPokemonByLocation(trainer.getId(), PokemonLocation.TEAM);
        return ResponseEntity.ok(pokemonTeam);
    }

    @GetMapping("/storage")
    public ResponseEntity<List<PokemonInfoResponse>> getStoragePokemon(Authentication authentication) {
        AuthenticatedTrainer trainer = (AuthenticatedTrainer) authentication.getPrincipal();

        List<PokemonInfoResponse> storedPokemon = pokemonQueryService.findPokemonByLocation(trainer.getId(), PokemonLocation.STORAGE);
        return ResponseEntity.ok(storedPokemon);
    }

    @GetMapping("/summary")
    public ResponseEntity<PokemonSummaryResponse> getPokemonSummary(Authentication authentication) {
        AuthenticatedTrainer trainer = (AuthenticatedTrainer) authentication.getPrincipal();

        PokemonSummaryResponse pokemonSummary = pokemonQueryService.getTrainerPokemonSummary(trainer.getId());
        return ResponseEntity.ok(pokemonSummary);
    }
    
}
