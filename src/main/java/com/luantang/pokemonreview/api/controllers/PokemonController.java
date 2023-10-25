package com.luantang.pokemonreview.api.controllers;

import com.luantang.pokemonreview.api.dto.PokemonDto;
import com.luantang.pokemonreview.api.models.Pokemon;
import com.luantang.pokemonreview.api.services.PokemonService;
import com.luantang.pokemonreview.api.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PokemonController {


    private PokemonService pokemonService;

    private SequenceGeneratorService sequenceGenaratorService;

    @Autowired
    public PokemonController(PokemonService pokemonService, SequenceGeneratorService sequenceGenaratorService) {
        this.pokemonService = pokemonService;
        this.sequenceGenaratorService = sequenceGenaratorService;
    }

    @GetMapping("pokemons")
    public ResponseEntity<List<PokemonDto>> getPokemons() {
        return new ResponseEntity<>(pokemonService.getAllPokemons(), HttpStatus.OK);
    }

    @GetMapping("pokemon/{id}")
    public Pokemon pokemonDetail(@PathVariable int id) {
        return new Pokemon("Squirtle", "Water");
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
        pokemonDto.setId(sequenceGenaratorService.getSequenceNumber(Pokemon.SEQUENCE_NAME));
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<Pokemon> updatePokemon(@RequestBody Pokemon pokemon, @PathVariable("id") int pokemonId) {
        System.out.println(pokemon.getName());
        System.out.println(pokemon.getType());
        return ResponseEntity.ok(pokemon);
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") int pokemonId) {
        System.out.println(pokemonId);
        return ResponseEntity.ok("Deleted");
    }
}
