package com.bookorange.api.service;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Role;
import com.bookorange.api.dto.appuserDto.AppUserCourseEditDTO;
import com.bookorange.api.dto.appuserDto.AppUserDTO;
import com.bookorange.api.dto.appuserDto.UserCreateDTO;

import java.util.List;

public interface AppUserService {
    AppUser create(UserCreateDTO userCreateDTO, Role role);

    AppUser update(AppUserDTO appUserDTO);

    AppUser findById(Long userId);

    AppUser findByUsername(String username);

    List<AppUser> findAll();

    void remove(Long userId);

    void addSubscribedCourse(AppUserCourseEditDTO userDto);

    void removeSubscribedCourse(AppUserCourseEditDTO userDto);

    void addArchivedCourse(AppUserCourseEditDTO userDto);

    void removeArchivedCourse(AppUserCourseEditDTO userDto);

    void addMyCourse(AppUserCourseEditDTO userDto);

    void removeMyCourse(AppUserCourseEditDTO userDto);

    void finishCourse(AppUserCourseEditDTO userDto);
}
