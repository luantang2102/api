package com.luantang.pokemonreview.api.services;

import com.luantang.pokemonreview.api.dto.PokemonDto;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
}
