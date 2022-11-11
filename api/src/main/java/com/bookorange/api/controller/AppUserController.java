package com.bookorange.api.controller;


import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Course;
import com.bookorange.api.domain.Lesson;
import com.bookorange.api.domain.Role;
import com.bookorange.api.dto.appuserDto.*;
import com.bookorange.api.dto.watchedDto.SetWatchedLessonDTO;
import com.bookorange.api.dto.watchedDto.WatchedLessonDTO;
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
    public ResponseEntity<Void> setWatched(@RequestBody SetWatchedLessonDTO setWatchedLessonDTO) {
        try {
            Lesson lesson = lessonService.findById(setWatchedLessonDTO.getLessonId());
            AppUser user = appUserService.findById(setWatchedLessonDTO.getUserId());
            watchedListService.setWatched(new WatchedLessonDTO(user, lesson));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/unwatched")
    public ResponseEntity<Void> setUnwatched(@RequestBody SetWatchedLessonDTO setWatchedLessonDTO) {
        try {
            Lesson lesson = lessonService.findById(setWatchedLessonDTO.getLessonId());
            AppUser user = appUserService.findById(setWatchedLessonDTO.getUserId());
            watchedListService.setUnwatched(new WatchedLessonDTO(user, lesson));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<AppUserCourseDTO> getUserCourses(@PathVariable("id") Long id) {
        try {
            AppUser user = appUserService.findById(id);
            List<Long> watchedList = watchedListService.getWatchedLessonList(user);
            AppUserCourseDTO userCourseDTO = new AppUserCourseDTO(user, watchedList);

            return ResponseEntity.ok(userCourseDTO);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @PostMapping(value = "/addSubscribedCourses")
    public ResponseEntity<Void> addSubscribedCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());
            appUserService.addSubscribedCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/removeSubscribedCourses")
    public ResponseEntity<Void> removeSubscribedCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());
            appUserService.removeSubscribedCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(value = "/addArchivedCourses")
    public ResponseEntity<Void> addArchivedCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());
            appUserService.addArchivedCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/removeArchivedCourses")
    public ResponseEntity<Void> removeArchivedCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());
            appUserService.removeArchivedCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(value = "/addMyCourses")
    public ResponseEntity<Void> addMyCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());
            appUserService.addMyCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/removeMyCourses")
    public ResponseEntity<Void> removeMyCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());
            appUserService.removeMyCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping(value = "/finishCourses")
    public ResponseEntity<Void> finishCourse(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());
            appUserService.finishCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}