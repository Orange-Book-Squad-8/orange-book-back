package com.bookorange.api.controller;


import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Course;
import com.bookorange.api.domain.Lesson;
import com.bookorange.api.domain.Role;
import com.bookorange.api.dto.appuserDto.*;
import com.bookorange.api.dto.watchedDto.SetWatchedLessonDTO;
import com.bookorange.api.dto.watchedDto.WatchedLessonDTO;
import com.bookorange.api.handler.exception.ForbiddenException;
import com.bookorange.api.handler.exception.ObjectNotFoundException;
import com.bookorange.api.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;
    private final CourseService courseService;
    private final LessonService lessonService;
    private final RoleService roleService;
    private final AppUserWatchedLessonService watchedListService;

    @PostMapping(value = "/create")
    public ResponseEntity<AppUserDTO> createAppUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        Role role = roleService.findByName(userCreateDTO.getRole());
        AppUser createdAppUser = appUserService.create(userCreateDTO, role);
        return ResponseEntity.ok(new AppUserDTO(createdAppUser));
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<AppUserDTO> editAppUser(@Valid @RequestBody AppUserDTO appUserDTO) {
        AppUser editedUser = appUserService.update(appUserDTO);
        return ResponseEntity.ok(new AppUserDTO(editedUser));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AppUserDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            AppUser user = appUserService.findByUsername(userLoginDTO.getUsername());
            if (user == null) {
                throw new ForbiddenException("User incorrect");
            }

            if (Objects.equals(user.getPassword(), userLoginDTO.getPassword())) {
                return ResponseEntity.ok(new AppUserDTO(user));
            }
            throw new ForbiddenException("Password incorrect");
        } catch (ForbiddenException e) {
            throw new ForbiddenException("User incorrect");
        } catch (Exception e) {
            throw new RuntimeException("Could not login");
        }
    }

    @PutMapping("/watched")
    public ResponseEntity<Void> setWatched(@RequestBody SetWatchedLessonDTO setWatchedLessonDTO) {
       try{
           Lesson lesson = lessonService.findById(setWatchedLessonDTO.getLessonId());
           AppUser user = appUserService.findById(setWatchedLessonDTO.getUserId());
           if (lesson == null || user == null) {
               throw new ObjectNotFoundException("Id not found");
           }
           watchedListService.setWatched(new WatchedLessonDTO(user, lesson));
           return new ResponseEntity<>(HttpStatus.OK);
       }catch(ObjectNotFoundException e){
           throw new ObjectNotFoundException("Id not found");
       }
    }

    @PutMapping("/unwatched")
    public ResponseEntity<Void> setUnwatched(@RequestBody SetWatchedLessonDTO setWatchedLessonDTO) {
        try {
            Lesson lesson = lessonService.findById(setWatchedLessonDTO.getLessonId());
            AppUser user = appUserService.findById(setWatchedLessonDTO.getUserId());
            if (lesson == null || user == null) {
                throw new ObjectNotFoundException("Id not found");
            }
            watchedListService.setUnwatched(new WatchedLessonDTO(user, lesson));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Id not found");
        }
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<AppUserCourseDTO> getUserCourses(@PathVariable("id") Long id) {
        try {
            AppUser user = appUserService.findById(id);
            if (user == null) {
                throw new ObjectNotFoundException("Id not found");
            }

            List<Long> watchedList = watchedListService.getWatchedLessonList(user);
            AppUserCourseDTO userCourseDTO = new AppUserCourseDTO(user, watchedList);
            return ResponseEntity.ok(userCourseDTO);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Id not found");
        }
    }

    @PostMapping(value = "/addSubscribedCourses")
    public ResponseEntity<Void> addSubscribedCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());

            if(course == null || userDTO.getUserId() == null){
                throw new ObjectNotFoundException("Id not found");
            }

            appUserService.addSubscribedCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Id not found");
        }
    }

    @DeleteMapping(value = "/removeSubscribedCourses")
    public ResponseEntity<Void> removeSubscribedCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());

            if(course == null || userDTO.getUserId() == null){
                throw new ObjectNotFoundException("Id not found");
            }

            appUserService.removeSubscribedCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Id not found");
        }
    }

    @PostMapping(value = "/addArchivedCourses")
    public ResponseEntity<Void> addArchivedCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());

            if(course == null || userDTO.getUserId() == null){
                throw new ObjectNotFoundException("Id not found");
            }

            appUserService.addArchivedCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Id not found");
        }
    }

    @DeleteMapping(value = "/removeArchivedCourses")
    public ResponseEntity<Void> removeArchivedCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());

            if(course == null || userDTO.getUserId() == null){
                throw new ObjectNotFoundException("Id not found");
            }

            appUserService.removeArchivedCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Id not found");
        }
    }

    @PostMapping(value = "/addMyCourses")
    public ResponseEntity<Void> addMyCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());

            if(course == null || userDTO.getUserId() == null){
                throw new ObjectNotFoundException("Id not found");
            }

            appUserService.addMyCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Id not found");
        }
    }

    @DeleteMapping(value = "/removeMyCourses")
    public ResponseEntity<Void> removeMyCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());

            if(course == null || userDTO.getUserId() == null){
                throw new ObjectNotFoundException("Id not found");
            }

            appUserService.removeMyCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Id not found");
        }
    }

    @PutMapping(value = "/finishCourses")
    public ResponseEntity<Void> finishCourse(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());

            if(course == null || userDTO.getUserId() == null){
                throw new ObjectNotFoundException("Id not found");
            }

            appUserService.finishCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Id not found");
        }
    }


}