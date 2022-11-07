package com.bookorange.api.controller;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Role;
import com.bookorange.api.dto.appuserDto.*;
import com.bookorange.api.dto.watchedDto.WatchedLessonDTO;
import com.bookorange.api.service.WatchedListService;
import com.bookorange.api.service.implementation.AppUserServiceImp;
import com.bookorange.api.service.implementation.RoleServiceImp;
import com.bookorange.api.service.implementation.WatchedListServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

public class AppUserController {

    @Autowired
    private AppUserServiceImp appUserServiceImp;

    @Autowired
    private RoleServiceImp roleServiceImp;

    @Autowired
    private WatchedListServiceImp watchedListServiceImp;



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
    public ResponseEntity<AppUserDTO> editAppUser(@RequestBody AppUserDTO appUserDTO, @PathVariable("appUserId") Long appUserId) {
        try {
            AppUser editedUser = appUserServiceImp.update(appUserDTO);
            return ResponseEntity.ok(new AppUserDTO(editedUser));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AppUserDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            AppUser user = appUserServiceImp.findByUsername(userLoginDTO.getUsername());
            if (Objects.equals(user.getPassword(), userLoginDTO.getPassword())) {
                return ResponseEntity.ok(new AppUserDTO(user));
            }
            throw new RuntimeException("Password incorrect");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/watched")
    public ResponseEntity<Void> setWatched(@RequestBody WatchedLessonDTO watchedLessonDTO){
        try{
            watchedListServiceImp.setWatched(watchedLessonDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/unwatched")
    public ResponseEntity<Void> setUnwatched(@RequestBody WatchedLessonDTO watchedLessonDTO){
        try{
            watchedListServiceImp.setUnwatched(watchedLessonDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<AppUserCourseDTO> getUserCourses(@PathVariable Long id) {
        try{
            AppUser user = appUserServiceImp.findById(id);
            AppUserCourseDTO userCourseDTO = new AppUserCourseDTO();
            userCourseDTO.setMyCourses(user.getMyCourses());
            // VOLTAR AQUI - CURSOS ASSISTIDOS NÃO ESTÃO RETORNANDO
            return ResponseEntity.ok(userCourseDTO);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }


    @PostMapping(value = "/addSuscribedCourses")
    public ResponseEntity<AppUserCourseEditDTO> addSubscribedCourses(@RequestBody AppUserCourseEditDTO userDTO){
        try{
            appUserServiceImp.addSubscribedCourse(userDTO);
            return ResponseEntity.ok(userDTO);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    @DeleteMapping(value = "/removeSuscribedCourses")
    public ResponseEntity<Void> removeSubscribedCourses(@RequestBody AppUserCourseEditDTO userDTO){
        try{
            appUserServiceImp.removeSubscribedCourse(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(value = "/addArchivedCourses")
    public ResponseEntity<AppUserCourseEditDTO> addArchivedCourses(@RequestBody AppUserCourseEditDTO userDTO){
        try{
            appUserServiceImp.addArchivedCourse(userDTO);
            return ResponseEntity.ok(userDTO);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/removeArchivedCourses")
    public ResponseEntity<Void> removeArchivedCourses(@RequestBody AppUserCourseEditDTO userDTO){
        try{
            appUserServiceImp.removeArchivedCourse(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(value = "/addMyCourses")
    public ResponseEntity<AppUserCourseEditDTO> addMyCourses(@RequestBody AppUserCourseEditDTO userDTO){
        try{
            appUserServiceImp.addMyCourse(userDTO);
            return ResponseEntity.ok(userDTO);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/removeMyCourses")
    public ResponseEntity<Void> removeMyCourses(@RequestBody AppUserCourseEditDTO userDTO){
        try{
            appUserServiceImp.removeMyCourse(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    @PutMapping(value = "/finishCourses")
    public ResponseEntity<AppUserCourseEditDTO> finishCourse(@RequestBody AppUserCourseEditDTO userDTO) {
        try {
            appUserServiceImp.finishCourse(userDTO);
            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}