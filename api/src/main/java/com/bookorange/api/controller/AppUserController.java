package com.bookorange.api.controller;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Role;
import com.bookorange.api.dto.appuserDto.AppUserDTO;
import com.bookorange.api.dto.appuserDto.UserCreateDTO;
import com.bookorange.api.dto.appuserDto.UserLoginDTO;
import com.bookorange.api.service.implementation.AppUserServiceImp;
import com.bookorange.api.service.implementation.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/user")
public class AppUserController {

    private AppUserServiceImp appUserServiceImp;

    private RoleServiceImp roleServiceImp;


    @Autowired
    public AppUserController(AppUserServiceImp appUserServiceImp) {
        this.appUserServiceImp = appUserServiceImp;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<AppUserDTO> createAppUser(@RequestBody UserCreateDTO userCreateDTO) {
        try {
            Role role = roleServiceImp.findByName(userCreateDTO.getRole());
            AppUser createdAppUser = appUserServiceImp.create(userCreateDTO, role);
            return ResponseEntity.ok(new AppUserDTO(createdAppUser));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/{appUserId/edit")
    public ResponseEntity<AppUserDTO> editAppUser(@RequestBody AppUserDTO appUserDTO, @PathVariable("appUserId") Long appUserId){
        try {
            AppUser editedUser = appUserServiceImp.update(appUserDTO);
            return ResponseEntity.ok(new AppUserDTO(editedUser));
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AppUserDTO> login(@RequestBody UserLoginDTO userLoginDTO){
        try {
            AppUser user = appUserServiceImp.findByUsername(userLoginDTO.getUsername());
            if (Objects.equals(user.getPassword(), userLoginDTO.getPassword())) {
                return ResponseEntity.ok(new AppUserDTO(user));
            }
            throw new RuntimeException("Password incorrect");
        } catch(Exception e){
            throw new RuntimeException(e);
        }

    }
}
