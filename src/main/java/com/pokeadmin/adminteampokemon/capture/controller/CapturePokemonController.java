package com.pokeadmin.adminteampokemon.capture.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokeadmin.adminteampokemon.capture.dto.CapturePokemonRequest;
import com.pokeadmin.adminteampokemon.capture.dto.CapturePokemonResponse;
import com.pokeadmin.adminteampokemon.capture.service.CapturePokemonService;
import com.pokeadmin.adminteampokemon.security.AuthenticatedTrainer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/capture")
@RestController
public class CapturePokemonController {
    
    private final CapturePokemonService capturePokemonService;

    public CapturePokemonController(CapturePokemonService capturePokemonService) {
        this.capturePokemonService = capturePokemonService;
    }

    @Operation(
        summary = "Capture a Pokémon",
        description = "Captures a Pokémon for the authenticated trainer. If the team is full, the Pokémon is sent to storage.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pokemon captured successfully"),
        @ApiResponse(responseCode = "409", description = "Pokemon already exists"),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/try-capture")
    public ResponseEntity<CapturePokemonResponse> capturePokemon(@RequestBody CapturePokemonRequest request, Authentication authentication) {
        AuthenticatedTrainer trainer = (AuthenticatedTrainer) authentication.getPrincipal();

        CapturePokemonResponse response = capturePokemonService.capturePokemon(request, trainer.getId());
        return ResponseEntity.ok(response);
    }
}
