package com.pokeadmin.adminteampokemon.capture.entity;

import java.time.LocalDateTime;

import com.pokeadmin.adminteampokemon.trainer.entity.TrainerEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "pokemon_capturado")
@NoArgsConstructor
public class CapturedPokemonEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pokemon_capturado")
    private Long capturedPokemonId;

    @ManyToOne
    @JoinColumn(name="id_entrenador", nullable = false)
    private TrainerEntity trainer;
    
    @Column(name = "num_pokedex", nullable = false)
    private Integer pokedexNumber;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PokemonLocation location;

    @Column(name = "fecha_captura", nullable = false)
    private LocalDateTime capturedAt;

    @Column(name= "sprite_url", nullable = true)
    private String spriteUrl;


}
