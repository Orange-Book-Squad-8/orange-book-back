package com.bookorange.api.controller;


import com.bookorange.api.domain.Course;
import com.bookorange.api.domain.Section;
import com.bookorange.api.dto.courseDto.*;
import com.bookorange.api.dto.sectionDto.SectionCreateDTO;
import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
import com.bookorange.api.service.CourseService;
import com.bookorange.api.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/courses")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final SectionService sectionService;

    @PostMapping(value = "/create")
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseCreateDTO courseCreateDTO) {
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
    public ResponseEntity<List<CourseDTO>> findAll() {
        try {
            List<Course> allCourses = courseService.findAll();
            List<CourseDTO> collect = allCourses.stream().map(CourseDTO::new).toList();
            return ResponseEntity.ok(collect);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/category/{category}")
    public ResponseEntity<List<CourseDTO>> listByCategory(@PathVariable("category") StackCategories category) {
        try {
            List<Course> coursesByCategory = courseService.findByCategory(category);
            List<CourseDTO> collect = coursesByCategory.stream().map(CourseDTO::new).toList();
            return ResponseEntity.ok(collect);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/difficulty/{difficulty}")
    public ResponseEntity<List<CourseDTO>> listByDifficulty(@PathVariable("difficulty") Difficulty difficulty) {
        try {
            List<Course> coursesByDifficulty = courseService.findByDifficulty(difficulty);
            List<CourseDTO> collect = coursesByDifficulty.stream().map(CourseDTO::new).toList();
            return ResponseEntity.ok(collect);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/creator/{creator}")
    public ResponseEntity<List<CourseDTO>> listByCreator(@PathVariable("creator") String creator) {
        try {
            List<Course> coursesByCreator = courseService.findByCreator(creator);
            List<CourseDTO> collect = coursesByCreator.stream().map(CourseDTO::new).toList();
            return ResponseEntity.ok(collect);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Course> updateCourse(@Valid @RequestBody CourseDTO courseDTO) {
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
    public ResponseEntity<Course> addSection(@RequestBody CourseCreateSectionDTO courseSectionEditDTO) {
        try {
            Section section = sectionService.create(new SectionCreateDTO(courseSectionEditDTO.getSectionName()));
            courseService.addSection(new CourseSectionEditDTO(courseSectionEditDTO.getCourseId(), section));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/remove_section")
    public ResponseEntity<Void> removeSection(@RequestBody CourseRemoveSectionDTO courseSectionEditDTO) {
        try {
            Section section = sectionService.findById(courseSectionEditDTO.getSectionId());
            courseService.removeSection(new CourseSectionEditDTO(courseSectionEditDTO.getCourseId(), section));
            sectionService.delete(courseSectionEditDTO.getSectionId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/duration/{id}")
    public ResponseEntity<Integer> getCourseDuration(@PathVariable("id") Long courseId) {
        try {
            Integer courseDuration = courseService.getCourseDuration(courseId);
            return ResponseEntity.ok(courseDuration);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
