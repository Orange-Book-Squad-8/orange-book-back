package com.bookorange.api.service.implementation;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Role;
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
    public AppUser create(UserCreateDTO userCreateDTO, Role role) {
        return null;
    }

    @Override
    public AppUser update(AppUserDTO appUserDTO) {
        return null;
    }

    @Override
    public AppUser findByUsername(String username) {
        return null;
    }


    @Override
    public AppUser findById(Long userId) {
        return null;
    }

    @Override
    public List<AppUser> findAll() {
        return null;
    }

    @Override
    public void remove(Long userId) {

    }
}
