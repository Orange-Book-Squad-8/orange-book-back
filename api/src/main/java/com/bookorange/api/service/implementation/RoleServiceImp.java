package com.bookorange.api.service.implementation;

import com.bookorange.api.dto.roleDto.RoleCreateDTO;
import com.bookorange.api.dto.roleDto.RoleDTO;
import com.bookorange.api.repository.RoleRepository;
import com.bookorange.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDTO create(RoleCreateDTO roleCreateDTO) {
        return null;
    }

    @Override
    public RoleDTO findByName(String roleName) {
        return null;
    }

    @Override
    public List<RoleDTO> findAll() {
        return null;
    }

    @Override
    public RoleDTO update(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public void delete(Long roleId) {

    }
}
