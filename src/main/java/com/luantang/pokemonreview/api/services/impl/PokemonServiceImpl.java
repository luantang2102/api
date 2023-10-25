package com.luantang.pokemonreview.api.services.impl;

import com.luantang.pokemonreview.api.dto.PokemonDto;
import com.luantang.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.luantang.pokemonreview.api.models.Pokemon;
import com.luantang.pokemonreview.api.repositories.PokemonRepository;
import com.luantang.pokemonreview.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {
    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public List<PokemonDto> getAllPokemons() {
        List<Pokemon> pokemon = pokemonRepository.findAll();
        return pokemon.stream().map(pok -> mapToDto(pok)).collect(Collectors.toList());
    }

    @Override
    public PokemonDto getPokemonById(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found"));
        return mapToDto(pokemon);
    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be updated"));

        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon updatePokemon = pokemonRepository.save(pokemon);
        return mapToDto(pokemon);
    }

    @Override
    public void deletePokemon(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be deleted"));
        pokemonRepository.delete(pokemon);
    }

    private PokemonDto mapToDto(Pokemon pokemon) {
        return new PokemonDto(pokemon.getId(), pokemon.getName(), pokemon.getType());
    }

    private Pokemon mapToEntity(PokemonDto pokemonDto) {
        return new Pokemon(pokemonDto.getId(), pokemonDto.getName(), pokemonDto.getType());
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonDto.getId());
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        System.out.println(pokemon.getId());

        Pokemon newPokemon = pokemonRepository.save(pokemon);

        PokemonDto pokemonResponse = new PokemonDto(newPokemon.getId(), newPokemon.getName(), newPokemon.getType());
        return pokemonResponse;
    }


}
