package com.pokeadmin.adminteampokemon.pokemon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokeadmin.adminteampokemon.pokemon.dto.MovePokemonRequest;
import com.pokeadmin.adminteampokemon.pokemon.dto.MovePokemonResponse;
import com.pokeadmin.adminteampokemon.pokemon.dto.RenamePokemonRequest;
import com.pokeadmin.adminteampokemon.pokemon.service.PokemonManagementService;
import com.pokeadmin.adminteampokemon.security.AuthenticatedTrainer;

@RestController
@RequestMapping("/admin/pokemon")
public class PokemonManagementController {

    private final PokemonManagementService pokemonManagementService;

    public PokemonManagementController(PokemonManagementService pokemonManagementService) {
        this.pokemonManagementService = pokemonManagementService;
    }

    @DeleteMapping("/free/{capturedPokemonId}")
    public ResponseEntity<String> freePokemon(@PathVariable Long capturedPokemonId, Authentication authentication) {
        AuthenticatedTrainer trainer = (AuthenticatedTrainer) authentication.getPrincipal();
        pokemonManagementService.freePokemon(capturedPokemonId, trainer.getId());
        return ResponseEntity.ok("Pokemon freed successfully");
    }

    @PutMapping("/rename/{capturedPokemonId}")
    public ResponseEntity<String> renamePokemon(
            @PathVariable Long capturedPokemonId,
            @RequestBody RenamePokemonRequest renamePokemonRequest,
            Authentication authentication) {
        AuthenticatedTrainer trainer = (AuthenticatedTrainer) authentication.getPrincipal();
        pokemonManagementService.renamePokemon(capturedPokemonId, trainer.getId(), renamePokemonRequest.getNewName());
        return ResponseEntity.ok("Pokemon renamed successfully");
    }

    @PutMapping("/{capturedPokemonId}/location")
    public ResponseEntity<MovePokemonResponse> movePokemon(
            @PathVariable Long capturedPokemonId,
            @RequestBody MovePokemonRequest movePokemonRequest,
            Authentication authentication) {
        AuthenticatedTrainer trainer = (AuthenticatedTrainer) authentication.getPrincipal();
        MovePokemonResponse response = pokemonManagementService.movePokemon(
                capturedPokemonId,
                trainer.getId(),
                movePokemonRequest.getLocation());
        return ResponseEntity.ok(response);
    }
}
