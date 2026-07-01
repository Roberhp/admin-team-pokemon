package com.pokeadmin.adminteampokemon.trainer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokeadmin.adminteampokemon.trainer.entity.TrainerEntity;

public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {
    
    boolean existsByUsername(String username);

    Optional<TrainerEntity> findByUsername(String username);
}
 
