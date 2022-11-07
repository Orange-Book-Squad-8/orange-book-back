package com.bookorange.api.controller;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Role;
import com.bookorange.api.dto.appuserDto.AppUserDTO;
import com.bookorange.api.dto.appuserDto.UserCreateDTO;
import com.bookorange.api.dto.roleDto.RoleCreateDTO;
import com.bookorange.api.dto.roleDto.RoleDTO;
import com.bookorange.api.service.implementation.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    @Autowired
    private RoleServiceImp roleServiceImp;

    @PostMapping(value = "/create")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleCreateDTO roleCreateDTO) {
        try {
            Role createdRole = roleServiceImp.create(roleCreateDTO);
            return ResponseEntity.ok(new RoleDTO(createdRole));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
