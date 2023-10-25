package com.luantang.pokemonreview.api.services;

import com.luantang.pokemonreview.api.dto.PokemonDto;

import java.util.List;

public interface PokemonService {
    List<PokemonDto> getAllPokemons();
    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonDto getPokemonById(int id);
    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);
    void deletePokemon(int id);
}
