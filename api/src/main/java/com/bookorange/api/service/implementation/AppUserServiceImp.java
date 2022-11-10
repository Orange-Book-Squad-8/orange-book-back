package com.bookorange.api.service.implementation;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Role;
import com.bookorange.api.dto.appuserDto.AppUserCourseEditDTO;
import com.bookorange.api.dto.appuserDto.AppUserDTO;
import com.bookorange.api.dto.appuserDto.UserCreateDTO;
import com.bookorange.api.repository.AppUserRepository;
import com.bookorange.api.service.AppUserService;
import com.bookorange.api.service.exceptions.ObjectNotFoundException;
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
        user.setPassword(userCreateDTO.getPassword());
        user.setEmail(userCreateDTO.getEmail());
        user.setStackCategories(userCreateDTO.getStackCategories());
        user.setRole(role);

        return appUserRepository.save(user);
    }

    @Override
    public AppUser update(AppUserDTO appUserDTO) {
        AppUser user = findById(appUserDTO.getId());
        user.setRole(appUserDTO.getRole());
        user.setBadges(appUserDTO.getBadges());
        user.setEmail(appUserDTO.getEmail());
        user.setUsername(appUserDTO.getUsername());
        user.setStackCategories(appUserDTO.getStackCategories());
        return appUserRepository.save(user);
    }

    @Override
    public AppUser findById(Long userId) {
        return appUserRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User not found"));
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

    @Override
    public void addSubscribedCourse(AppUserCourseEditDTO userDto) {
        AppUser user = findById(userDto.getId());
        user.addSubscribedCourse(userDto.getCourse());
        appUserRepository.save(user);
    }

    @Override
    public void removeSubscribedCourse(AppUserCourseEditDTO userDto) {
        AppUser user = findById(userDto.getId());
        user.removeSubscribedCourse(userDto.getCourse());
        appUserRepository.save(user);
    }

    @Override
    public void addArchivedCourse(AppUserCourseEditDTO userDto) {
        AppUser user = findById(userDto.getId());
        user.addArchivedCourse(userDto.getCourse());
        appUserRepository.save(user);
    }

    @Override
    public void removeArchivedCourse(AppUserCourseEditDTO userDto) {
        AppUser user = findById(userDto.getId());
        user.removeArchivedCourse(userDto.getCourse());
        appUserRepository.save(user);
    }

    @Override
    public void addMyCourse(AppUserCourseEditDTO userDto) {
        AppUser user = findById(userDto.getId());
        user.addMyCourse(userDto.getCourse());
        appUserRepository.save(user);
    }

    @Override
    public void removeMyCourse(AppUserCourseEditDTO userDto) {
        AppUser user = findById(userDto.getId());
        user.removeMyCourse(userDto.getCourse());
        appUserRepository.save(user);
    }

    @Override
    public void finishCourse(AppUserCourseEditDTO userDto) {
        AppUser user = findById(userDto.getId());
        user.finishCourse(userDto.getCourse());
        appUserRepository.save(user);
    }

}
