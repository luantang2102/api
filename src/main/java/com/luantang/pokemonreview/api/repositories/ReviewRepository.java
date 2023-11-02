package com.luantang.pokemonreview.api.repositories;

import com.luantang.pokemonreview.api.models.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, Integer> {

    @Query(value = "{'pokemon.id' : ?0}")
    List<Review> findByPokemonId(@Param("id") int pokemonId);

}
