package com.luantang.pokemonreview.api.repositories;

import com.luantang.pokemonreview.api.models.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PokemonRepository extends MongoRepository<Pokemon, String> {
}
