package com.luantang.pokemonreview.api.services;

import com.luantang.pokemonreview.api.dto.PokemonDto;

import java.util.List;

public interface PokemonService {
    List<PokemonDto> getAllPokemons();
    PokemonDto createPokemon(PokemonDto pokemonDto);

}
