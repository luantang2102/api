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
    public ResponseEntity<PokemonDto> pokemonDetail(@PathVariable int id) {
        return ResponseEntity.ok(pokemonService.getPokemonById(id));
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
        pokemonDto.setId(sequenceGenaratorService.getSequenceNumber(Pokemon.SEQUENCE_NAME));
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable("id") int pokemonId) {
        PokemonDto response = pokemonService.updatePokemon(pokemonDto, pokemonId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") int id) {
        pokemonService.deletePokemon(id);
        return new ResponseEntity<>("Pokemon deleted!", HttpStatus.OK);
    }
}
