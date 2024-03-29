package com.bookorange.api.controller;

import com.bookorange.api.domain.Role;
import com.bookorange.api.dto.roleDto.RoleCreateDTO;
import com.bookorange.api.dto.roleDto.RoleDTO;
import com.bookorange.api.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping(value = "/roles")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping(value = "/create")
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody RoleCreateDTO roleCreateDTO) {
        try {
            Role createdRole = roleService.create(roleCreateDTO);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/create").buildAndExpand(createdRole.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
