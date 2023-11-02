package com.luantang.pokemonreview.api.services.impl;

import com.luantang.pokemonreview.api.exceptions.RoleNotFoundException;
import com.luantang.pokemonreview.api.models.Role;
import com.luantang.pokemonreview.api.repositories.RoleRepository;
import com.luantang.pokemonreview.api.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException("Role with associate name not found"));
    }
}
