package com.pokeadmin.adminteampokemon.trainer.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.pokeadmin.adminteampokemon.capture.entity.CapturedPokemonEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "entrenador")
@NoArgsConstructor
public class TrainerEntity {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_entrenador", nullable = false) 
    private Long trainerId;
    
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "nombre_entrenador", nullable = false)
    private String trainerName;

    @Column(name = "pueblo_origen")
    private String originTown;

    @Column(name = "fecha_registro")
    private LocalDateTime registeredAt;

    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    private List<CapturedPokemonEntity> capturedPokemons;

    @Column
    private String region;

}
