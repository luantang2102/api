package com.luantang.pokemonreview.api.controllers;

import com.luantang.pokemonreview.api.models.Pokemon;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PokemonController {

    @GetMapping("pokemons")
    public RequestEntity<List<Pokemon>> getPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();
        
    }
}
