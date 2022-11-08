package com.bookorange.api.controller;


import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Role;
import com.bookorange.api.dto.appuserDto.*;
import com.bookorange.api.dto.watchedDto.WatchedLessonDTO;
import com.bookorange.api.service.AppUserService;
import com.bookorange.api.service.AppUserWatchedLessonService;
import com.bookorange.api.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    private final RoleService roleService;

    private final AppUserWatchedLessonService watchedListService;

    @PostMapping(value = "/create")
    public ResponseEntity<AppUserDTO> createAppUser(@RequestBody UserCreateDTO userCreateDTO) {
        try {
            Role role = roleService.findByName(userCreateDTO.getRole());
            AppUser createdAppUser = appUserService.create(userCreateDTO, role);
            return ResponseEntity.ok(new AppUserDTO(createdAppUser));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/{appUserId}/edit")
    public ResponseEntity<AppUserDTO> editAppUser(@RequestBody AppUserDTO appUserDTO, @PathVariable("appUserId") Long appUserId) {
        try {
            AppUser editedUser = appUserService.update(appUserDTO);
            return ResponseEntity.ok(new AppUserDTO(editedUser));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AppUserDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            AppUser user = appUserService.findByUsername(userLoginDTO.getUsername());
            if (Objects.equals(user.getPassword(), userLoginDTO.getPassword())) {
                return ResponseEntity.ok(new AppUserDTO(user));
            }
            throw new RuntimeException("Password incorrect");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/watched")
    public ResponseEntity<Void> setWatched(@RequestBody WatchedLessonDTO watchedLessonDTO) {
        try {
            watchedListService.setWatched(watchedLessonDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/unwatched")
    public ResponseEntity<Void> setUnwatched(@RequestBody WatchedLessonDTO watchedLessonDTO) {
        try {
            watchedListService.setUnwatched(watchedLessonDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<AppUserCourseDTO> getUserCourses(@PathVariable Long id) {
        try {
            AppUser user = appUserService.findById(id);
            List<Long> watchedList = watchedListService.getWatchedLessonList(id);
            AppUserCourseDTO userCourseDTO = new AppUserCourseDTO(user, watchedList);

            return ResponseEntity.ok(userCourseDTO);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @PostMapping(value = "/addSuscribedCourses")
    public ResponseEntity<AppUserCourseEditDTO> addSubscribedCourses(@RequestBody AppUserCourseEditDTO userDTO) {
        try {
            appUserService.addSubscribedCourse(userDTO);
            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/removeSuscribedCourses")
    public ResponseEntity<Void> removeSubscribedCourses(@RequestBody AppUserCourseEditDTO userDTO) {
        try {
            appUserService.removeSubscribedCourse(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(value = "/addArchivedCourses")
    public ResponseEntity<AppUserCourseEditDTO> addArchivedCourses(@RequestBody AppUserCourseEditDTO userDTO) {
        try {
            appUserService.addArchivedCourse(userDTO);
            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/removeArchivedCourses")
    public ResponseEntity<Void> removeArchivedCourses(@RequestBody AppUserCourseEditDTO userDTO) {
        try {
            appUserService.removeArchivedCourse(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(value = "/addMyCourses")
    public ResponseEntity<AppUserCourseEditDTO> addMyCourses(@RequestBody AppUserCourseEditDTO userDTO) {
        try {
            appUserService.addMyCourse(userDTO);
            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/removeMyCourses")
    public ResponseEntity<Void> removeMyCourses(@RequestBody AppUserCourseEditDTO userDTO) {
        try {
            appUserService.removeMyCourse(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping(value = "/finishCourses")
    public ResponseEntity<AppUserCourseEditDTO> finishCourse(@RequestBody AppUserCourseEditDTO userDTO) {
        try {
            appUserService.finishCourse(userDTO);
            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}