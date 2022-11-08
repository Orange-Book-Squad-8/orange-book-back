package com.bookorange.api.controller;


import com.bookorange.api.domain.Course;
import com.bookorange.api.dto.courseDto.CourseCreateDTO;
import com.bookorange.api.dto.courseDto.CourseDTO;
import com.bookorange.api.dto.courseDto.CourseSectionEditDTO;
import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
import com.bookorange.api.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/courses")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping(value = "/create")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseCreateDTO courseCreateDTO) {
        try {
            Course courseCreated = courseService.create(courseCreateDTO);
            return ResponseEntity.ok(new CourseDTO(courseCreated));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<Course> findById(@RequestParam("courseId") Long courseId) {
        try {
            Course course = courseService.findById(courseId);
            return ResponseEntity.ok(course);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Course>> findAll() {
        try {
            List<Course> allCourses = courseService.findAll();
            return ResponseEntity.ok(allCourses);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/category/{category}")
    public ResponseEntity<List<Course>> listByCategory(@PathVariable("category") StackCategories category) {
        try {
            List<Course> coursesByCategory = courseService.findByCategory(category);
            return ResponseEntity.ok(coursesByCategory);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/difficulty/{difficulty}")
    public ResponseEntity<List<Course>> listByDifficulty(@PathVariable("difficulty") Difficulty difficulty) {
        try {
            List<Course> coursesByDifficulty = courseService.findByDifficulty(difficulty);
            return ResponseEntity.ok(coursesByDifficulty);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Course> updateCourse(@RequestBody CourseDTO courseDTO) {
        try {
            Course courseUpdate = courseService.update(courseDTO);
            return ResponseEntity.ok(courseUpdate);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteCourse(@RequestParam("courseId") Long courseId) {
        try {
            courseService.delete(courseId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/new_section")
    public ResponseEntity<Course> addSection(@RequestBody CourseSectionEditDTO courseSectionEditDTO) {
        try {
            courseService.addSection(courseSectionEditDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/remove_section")
    public ResponseEntity<Void> removeSection(@RequestBody CourseSectionEditDTO courseSectionEditDTO) {
        try {
            courseService.addSection(courseSectionEditDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/duration/{id}")
    public ResponseEntity<Integer> getCourseDuration(@RequestParam("courseId") Long courseId) {
        try {
            Integer courseDuration = courseService.getCourseDuration(courseId);
            return ResponseEntity.ok(courseDuration);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
