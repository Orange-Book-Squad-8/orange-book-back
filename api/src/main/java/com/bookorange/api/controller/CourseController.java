package com.bookorange.api.controller;


import com.bookorange.api.domain.Course;
import com.bookorange.api.domain.Lesson;
import com.bookorange.api.domain.Section;
import com.bookorange.api.dto.courseDto.*;
import com.bookorange.api.dto.sectionDto.CompleteSectionDTO;
import com.bookorange.api.dto.sectionDto.SectionCreateDTO;
import com.bookorange.api.dto.sectionDto.SectionEditLessonDTO;
import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
import com.bookorange.api.service.AppUserService;
import com.bookorange.api.service.CourseService;
import com.bookorange.api.service.LessonService;
import com.bookorange.api.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/courses")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final SectionService sectionService;
    private final LessonService lessonService;

    private final AppUserService appUserService;

    @PostMapping(value = "/create")
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseCreateDTO courseCreateDTO) {
        try {
            List<Section> sections = courseCreateDTO.getSections()
                    .stream()
                    .map(section -> {
                        Section created = sectionService.create(new SectionCreateDTO(section.getName()));
                        section.getLessons().forEach(lessonId -> {
                            Lesson lesson = lessonService.findById(lessonId);
                            sectionService.addLesson(new SectionEditLessonDTO(created.getId(), lesson));
                        });
                        return created;
                    }).toList();
            courseCreateDTO.setSections(sections);
            Course courseCreated = courseService.create(courseCreateDTO);
            return ResponseEntity.ok(new CourseDTO(courseCreated));
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompleteCourseDTO> findById(@PathVariable("id") Long courseId) {
        try {
            Course course = courseService.findById(courseId);
            CompleteCourseDTO dto = new CompleteCourseDTO();
            dto.setCourseDTO(new CourseDTO(course));
            course.getSections().forEach(section -> {
                CompleteSectionDTO sectionDTO = new CompleteSectionDTO();
                sectionDTO.setId(section.getId());
                sectionDTO.setName(section.getName());
                sectionDTO.setLessons(section.getLessons().stream().map(lessonService::findById).toList());
                dto.addSection(sectionDTO);
            });
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<CourseDTO>> findAll() {
        try {
            List<Course> allCourses = courseService.findAll();
            List<CourseDTO> collect = allCourses.stream().map(CourseDTO::new).toList();
            return ResponseEntity.ok(collect);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping(value = "/category/{category}")
    public ResponseEntity<List<CourseDTO>> listByCategory(@PathVariable("category") StackCategories category) {
        try {
            List<Course> coursesByCategory = courseService.findByCategory(category);
            List<CourseDTO> collect = coursesByCategory.stream().map(CourseDTO::new).toList();
            return ResponseEntity.ok(collect);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping(value = "/difficulty/{difficulty}")
    public ResponseEntity<List<CourseDTO>> listByDifficulty(@PathVariable("difficulty") Difficulty difficulty) {
        try {
            List<Course> coursesByDifficulty = courseService.findByDifficulty(difficulty);
            List<CourseDTO> collect = coursesByDifficulty.stream().map(CourseDTO::new).toList();
            return ResponseEntity.ok(collect);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping(value = "/creator/{creator}")
    public ResponseEntity<List<CourseDTO>> listByCreator(@PathVariable("creator") String creator) {
        try {
            List<Course> coursesByCreator = courseService.findByCreator(creator);
            List<CourseDTO> collect = coursesByCreator.stream().map(CourseDTO::new).toList();
            return ResponseEntity.ok(collect);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Course> updateCourse(@Valid @RequestBody CourseEditDTO courseDTO) {
        try {
            Course courseUpdate = courseService.update(courseDTO);
            courseDTO.getDeletedSectionIds().forEach(sectionService::delete);
            return ResponseEntity.ok(courseUpdate);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteCourse(@RequestParam("courseId") Long courseId) {
        try {
            courseService.delete(courseId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(value = "/new_section")
    public ResponseEntity<Course> addSection(@RequestBody CourseCreateSectionDTO courseSectionEditDTO) {
        try {
            Section section = sectionService.create(new SectionCreateDTO(courseSectionEditDTO.getSectionName()));
            courseService.addSection(new CourseSectionEditDTO(courseSectionEditDTO.getCourseId(), section));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
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
            throw new RuntimeException(e.getMessage());
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

}
