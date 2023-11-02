package com.luantang.pokemonreview.api.controllers;

import com.luantang.pokemonreview.api.models.Role;
import com.luantang.pokemonreview.api.services.RoleService;
import com.luantang.pokemonreview.api.services.SequenceGeneratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class RoleController {
    RoleService roleService;
    SequenceGeneratorService sequenceGeneratorService;

    public RoleController(RoleService roleService, SequenceGeneratorService sequenceGeneratorService) {
        this.roleService = roleService;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @PostMapping("role/create")
    public ResponseEntity<String> createRoles() {
        Role role1 = new Role();
        role1.setId(sequenceGeneratorService.getSequenceNumber(Role.SEQUENCE_NAME));
        role1.setName("ADMIN");
        roleService.createRole(role1);

        Role role2 = new Role();
        role2.setId(sequenceGeneratorService.getSequenceNumber(Role.SEQUENCE_NAME));
        role2.setName("USER");
        roleService.createRole(role2);

        return new ResponseEntity<>("Roles created", HttpStatus.CREATED);
    }
}
