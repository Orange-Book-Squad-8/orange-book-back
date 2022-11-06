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
        AppUser user = new AppUser();
        user.setUsername(userCreateDTO.getUsername());
        user.setEmail(userCreateDTO.getEmail());
        user.setContentType(userCreateDTO.getContentType());
        user.setRole(role);
        return appUserRepository.save(user);
    }

    @Override
    public AppUser update(AppUserDTO appUserDTO) {
        AppUser user = appUserRepository.findByUsername(appUserDTO.getUsername());
        user.setRole(appUserDTO.getRole());
        user.setBadges(appUserDTO.getBadges());
        user.setEmail(appUserDTO.getEmail());
        user.setUsername(appUserDTO.getUsername());
        user.setContentType(appUserDTO.getContentType());
        return appUserRepository.save(user);
    }

    @Override
    public AppUser findById(Long userId) {
        return appUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public void remove(Long userId) {
        appUserRepository.deleteById(userId);
    }

}
