package com.bookorange.api.service;

import com.bookorange.api.dto.appuserDto.AppUserDTO;
import com.bookorange.api.dto.appuserDto.UserCreateDTO;

import java.util.List;

public interface AppUserService {
    AppUserDTO create(UserCreateDTO userCreateDTO);

    AppUserDTO update(AppUserDTO appUserDTO);

    AppUserDTO findByUsername(String username);

    List<AppUserDTO> findAll();

    void remove(Long userId);
}
