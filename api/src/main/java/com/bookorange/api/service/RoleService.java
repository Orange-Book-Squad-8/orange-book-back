package com.bookorange.api.service;

import com.bookorange.api.dto.roleDto.RoleCreateDTO;
import com.bookorange.api.dto.roleDto.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO create(RoleCreateDTO roleCreateDTO);

    RoleDTO findByName(String roleName);

    List<RoleDTO> findAll();

    RoleDTO update(RoleDTO roleDTO);

    void delete(Long roleId);
}
