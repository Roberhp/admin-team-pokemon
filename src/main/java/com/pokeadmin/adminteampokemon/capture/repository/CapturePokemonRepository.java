package com.pokeadmin.adminteampokemon.capture.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokeadmin.adminteampokemon.capture.entity.CapturedPokemonEntity;
import com.pokeadmin.adminteampokemon.capture.entity.PokemonLocation;

public interface CapturePokemonRepository extends JpaRepository<CapturedPokemonEntity,Long> {
    
    long countByTrainerTrainerIdAndLocation(Long trainerId, PokemonLocation location);

    boolean existsByTrainerTrainerIdAndCapturedPokemonId(Long trainerId, Long capturedPokemonId);

    boolean existsByTrainerTrainerIdAndNicknameAndPokedexNumber(Long trainerId, String nickname, Integer pokedexNumber);
    
    Optional<CapturedPokemonEntity> findByTrainerTrainerIdAndCapturedPokemonId(Long trainerId, Long capturedPokemonId);

    Optional<CapturedPokemonEntity> findByCapturedPokemonId(Long capturedPokemonId);

    List<CapturedPokemonEntity> findByTrainerTrainerIdAndLocation(Long trainerId, PokemonLocation location);

    void deleteByCapturedPokemonId(Long capturedPokemonId);
    
}
