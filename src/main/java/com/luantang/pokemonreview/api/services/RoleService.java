package com.luantang.pokemonreview.api.services;

import com.luantang.pokemonreview.api.models.Role;

public interface RoleService {
    Role createRole(Role role);
    Role getRoleByName(String name);
}
