package com.bookorange.api.service;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Role;
import com.bookorange.api.dto.appuserDto.AppUserDTO;
import com.bookorange.api.dto.appuserDto.UserCreateDTO;

import java.util.List;

public interface AppUserService {
    AppUser create(UserCreateDTO userCreateDTO, Role role);

    AppUser update(AppUserDTO appUserDTO);

    AppUser findById(Long userId);

    List<AppUser> findAll();

    void remove(Long userId);
}
