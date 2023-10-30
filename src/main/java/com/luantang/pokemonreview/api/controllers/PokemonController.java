package com.luantang.pokemonreview.api.controllers;

import com.luantang.pokemonreview.api.dto.PokemonDto;
import com.luantang.pokemonreview.api.dto.PokemonResponse;
import com.luantang.pokemonreview.api.models.Pokemon;
import com.luantang.pokemonreview.api.services.PokemonService;
import com.luantang.pokemonreview.api.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/")
public class PokemonController {

    private PokemonService pokemonService;

    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public PokemonController(PokemonService pokemonService, SequenceGeneratorService sequenceGeneratorService) {
        this.pokemonService = pokemonService;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @GetMapping("pokemons")
    public ResponseEntity<PokemonResponse> getPokemons(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(pokemonService.getAllPokemons(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDto> pokemonDetail(@PathVariable int id) {
        return ResponseEntity.ok(pokemonService.getPokemonById(id));
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
        pokemonDto.setId(sequenceGeneratorService.getSequenceNumber(Pokemon.SEQUENCE_NAME));
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
