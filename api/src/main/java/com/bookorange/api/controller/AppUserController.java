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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "http://127.0.0.1:5173")
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

    @GetMapping(value = "/{id}/find")
    public ResponseEntity<CompleteAppUserDTO> findById(@PathVariable Long id) {
        try {
            AppUser user = appUserService.findById(id);

            return ResponseEntity.ok(new CompleteAppUserDTO(user));
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }

    @PutMapping("/watched")
    public ResponseEntity<Void> setWatched(@RequestBody SetWatchedLessonDTO setWatchedLessonDTO) {
        try {
            Lesson lesson = lessonService.findById(setWatchedLessonDTO.getLessonId());
            AppUser user = appUserService.findById(setWatchedLessonDTO.getUserId());

            watchedListService.setWatched(new WatchedLessonDTO(user, lesson));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }

    @PutMapping("/unwatched")
    public ResponseEntity<Void> setUnwatched(@RequestBody SetWatchedLessonDTO setWatchedLessonDTO) {
        try {
            Lesson lesson = lessonService.findById(setWatchedLessonDTO.getLessonId());
            AppUser user = appUserService.findById(setWatchedLessonDTO.getUserId());

            watchedListService.setUnwatched(new WatchedLessonDTO(user, lesson));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<AppUserCourseDTO> getUserCourses(@PathVariable("id") Long id) {
        try {
            AppUser user = appUserService.findById(id);
            Map<Long, List<Long>> watchedLesson = getLongListMap(user);

            AppUserCourseDTO userCourseDTO = new AppUserCourseDTO(user, watchedLesson);

            return ResponseEntity.ok(userCourseDTO);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/{id}/lessons")
    public ResponseEntity<Map<Long, List<Long>>> getUserLessons(@PathVariable("id") Long id) {
        try {
            AppUser user = appUserService.findById(id);
            Map<Long, List<Long>> watchedLesson = getLongListMap(user);
            return ResponseEntity.ok(watchedLesson);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }

    private Map<Long, List<Long>> getLongListMap(AppUser user) {
        List<Long> watchedList = watchedListService.getWatchedLessonList(user);

        List<Course> courseList = user.getSubscribedCourses();
        courseList.addAll(user.getArchivedCourses());
        courseList.addAll(user.getMyCourses());

        Map<Long, List<Long>> watchedLesson = new HashMap<>();
        List<List<Long>> coursesLessons = courseList.stream().map(Course::getLessons).toList();

        List<List<Long>> filteredList = coursesLessons.stream().map((lessons) -> lessons.stream().filter(watchedList::contains).toList()).toList();

        for (int i = 0; i < courseList.size(); i++) {
            watchedLesson.put(courseList.get(i).getId(), filteredList.get(i));
        }
        return watchedLesson;
    }


    @PostMapping(value = "/addSubscribedCourses")
    public ResponseEntity<Void> addSubscribedCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());
            appUserService.addSubscribedCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/removeSubscribedCourses")
    public ResponseEntity<Void> removeSubscribedCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());
            appUserService.removeSubscribedCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }

    @PostMapping(value = "/addArchivedCourses")
    public ResponseEntity<Void> addArchivedCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());
            appUserService.addArchivedCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/removeArchivedCourses")
    public ResponseEntity<Void> removeArchivedCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());
            appUserService.removeArchivedCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }

    @PostMapping(value = "/addMyCourses")
    public ResponseEntity<Void> addMyCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());
            appUserService.addMyCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/removeMyCourses")
    public ResponseEntity<Void> removeMyCourses(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());
            appUserService.removeMyCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }

    @PutMapping(value = "/finishCourses")
    public ResponseEntity<Void> finishCourse(@RequestBody AddCourseToUserDTO userDTO) {
        try {
            Course course = courseService.findById(userDTO.getCourseId());

            appUserService.finishCourse(new AppUserCourseEditDTO(userDTO.getUserId(), course));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }


}