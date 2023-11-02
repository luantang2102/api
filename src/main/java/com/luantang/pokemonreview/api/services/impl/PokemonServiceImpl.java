package com.luantang.pokemonreview.api.services.impl;

import com.luantang.pokemonreview.api.dto.PokemonDto;
import com.luantang.pokemonreview.api.dto.PokemonResponse;
import com.luantang.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.luantang.pokemonreview.api.models.Pokemon;
import com.luantang.pokemonreview.api.models.Review;
import com.luantang.pokemonreview.api.repositories.PokemonRepository;
import com.luantang.pokemonreview.api.repositories.ReviewRepository;
import com.luantang.pokemonreview.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {
    private PokemonRepository pokemonRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository, ReviewRepository reviewRepository) {
        this.pokemonRepository = pokemonRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public PokemonResponse getAllPokemons(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
        List<Pokemon> pokemonList = pokemons.getContent();
        List<PokemonDto> content = pokemonList.stream().map(pok -> mapToDto(pok)).collect(Collectors.toList());

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(content);
        pokemonResponse.setPageNo(pokemons.getNumber());
        pokemonResponse.setPageSize(pokemons.getSize());
        pokemonResponse.setTotalElements(pokemons.getTotalElements());
        pokemonResponse.setTotalPages(pokemons.getTotalPages());
        pokemonResponse.setLast(pokemons.isLast());

        return pokemonResponse;
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
        return mapToDto(updatePokemon);
    }

    @Override
    public void deletePokemon(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be deleted"));
        pokemonRepository.delete(pokemon);
        List<Review> reviewList = reviewRepository.findByPokemonId(pokemon.getId());
        reviewRepository.deleteAll(reviewList);
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

        Pokemon newPokemon = pokemonRepository.save(pokemon);

        PokemonDto pokemonResponse = new PokemonDto(newPokemon.getId(), newPokemon.getName(), newPokemon.getType());
        return pokemonResponse;
    }


}
