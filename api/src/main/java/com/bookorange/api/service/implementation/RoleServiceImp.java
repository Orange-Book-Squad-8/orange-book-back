package com.bookorange.api.service.implementation;

import com.bookorange.api.domain.Role;
import com.bookorange.api.dto.roleDto.RoleCreateDTO;
import com.bookorange.api.dto.roleDto.RoleDTO;
import com.bookorange.api.handler.exception.BadRequestException;
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
        Role role = new Role();
        role.setName(roleCreateDTO.getRoleName());
        return roleRepository.save(role);
    }

    @Override
    public Role findByName(String roleName) {
            Role role = roleRepository.findByName(roleName);
            if(role == null){
                throw new BadRequestException("Role incorrect!");
            }
            return role;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role update(RoleDTO roleDTO) {
        Role role = roleRepository.findById(roleDTO.getId()).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setName(roleDTO.getRoleName());
        return roleRepository.save(role);
    }

    @Override
    public void delete(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
