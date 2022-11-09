package com.bookorange.api.service;

import com.bookorange.api.domain.Role;
import com.bookorange.api.dto.roleDto.RoleCreateDTO;
import com.bookorange.api.dto.roleDto.RoleDTO;

import java.util.List;

public interface RoleService {
    Role create(RoleCreateDTO roleCreateDTO);

    Role findByName(String roleName);

    List<Role> findAll();

    Role update(RoleDTO roleDTO);

    void delete(Long roleId);
}
