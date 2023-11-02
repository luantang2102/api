package com.luantang.pokemonreview.api.repositories;

import com.luantang.pokemonreview.api.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
