package com.bookorange.api.service.implementation;

import com.bookorange.api.domain.Role;
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
    public Role create(RoleCreateDTO roleCreateDTO) {
        return null;
    }

    @Override
    public Role findByName(String roleName) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Role update(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public void delete(Long roleId) {

    }
}
