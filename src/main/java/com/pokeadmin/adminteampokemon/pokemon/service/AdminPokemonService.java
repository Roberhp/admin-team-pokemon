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
public class AdminPokemonService {

    private final Integer TEAM_LIMIT =6;
    private final Integer TEAM_BOX =30;

    
    private final CapturePokemonRepository capturePokemonRepository;

    public AdminPokemonService(CapturePokemonRepository capturePokemonRepository) {
        this.capturePokemonRepository = capturePokemonRepository;
    }   

    public void freePokemon(Long idPokemonCapturado, Long idTrainer) {
        if (capturePokemonRepository.existsByTrainerTrainerIdAndCapturedPokemonId(idTrainer, idPokemonCapturado)) {
            capturePokemonRepository.deleteByCapturedPokemonId(idPokemonCapturado);
        } else {
            throw new PokemonNotFoundException();
        }
    }

    public void renamePokemon(Long idPokemonCapturado, Long idTrainer, String newName) {
        CapturedPokemonEntity pokemon = capturePokemonRepository
                                                    .findByCapturedPokemonId(idPokemonCapturado)
                                                    .orElseThrow(PokemonNotFoundException::new);
        if (capturePokemonRepository.existsByTrainerTrainerIdAndNicknameAndPokedexNumber(idTrainer, newName, pokemon.getPokedexNumber())) {
            throw new PokemonAlreadyExistsException();
        }
        
        pokemon.setNickname(newName);
        capturePokemonRepository.save(pokemon);
    }

    public MovePokemonResponse movePokemon(Long idPokemonCapturado, Long IdTrainer, PokemonLocation newLocation) {
        CapturedPokemonEntity pokemon = capturePokemonRepository
                                                    .findByTrainerTrainerIdAndCapturedPokemonId(IdTrainer, idPokemonCapturado)
                                                    .orElseThrow(PokemonNotFoundException::new);

        validarNuevaAsignacion(IdTrainer,newLocation);

        pokemon.setLocation(newLocation);
        capturePokemonRepository.save(pokemon);
        return new MovePokemonResponse(pokemon.getCapturedPokemonId(), pokemon.getLocation().name());

    }

    private void validarNuevaAsignacion ( Long IdTrainer, PokemonLocation newLocation) {

        long totalPokemonNewLocation = capturePokemonRepository.countByTrainerTrainerIdAndLocation(IdTrainer, newLocation) ;
        switch(newLocation){
            case TEAM:
                if (totalPokemonNewLocation >= TEAM_LIMIT) 
                    throw new PokemonStorageFullException();
                
                break ;
            case STORAGE:
                if (totalPokemonNewLocation >= TEAM_BOX) 
                    throw new PokemonStorageFullException();
                
            break;
        }

    }

}
