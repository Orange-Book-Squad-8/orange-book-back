package com.bookorange.api.service.implementation;

import com.bookorange.api.dto.appuserDto.AppUserDTO;
import com.bookorange.api.dto.appuserDto.UserCreateDTO;
import com.bookorange.api.repository.AppUserRepository;
import com.bookorange.api.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImp implements AppUserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImp(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUserDTO create(UserCreateDTO userCreateDTO) {
        return null;
    }

    @Override
    public AppUserDTO update(AppUserDTO appUserDTO) {
        return null;
    }

    @Override
    public AppUserDTO findByUsername(String username) {
        return null;
    }

    @Override
    public List<AppUserDTO> findAll() {
        return null;
    }

    @Override
    public void remove(Long userId) {

    }
}
