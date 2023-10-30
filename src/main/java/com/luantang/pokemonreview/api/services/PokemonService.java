package com.luantang.pokemonreview.api.services;

import com.luantang.pokemonreview.api.dto.PokemonDto;
import com.luantang.pokemonreview.api.dto.PokemonResponse;

public interface PokemonService {
    PokemonResponse getAllPokemons(int pageNo, int pageSize);
    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonDto getPokemonById(int id);
    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);
    void deletePokemon(int id);
}
