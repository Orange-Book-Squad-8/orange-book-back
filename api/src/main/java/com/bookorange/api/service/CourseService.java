package com.bookorange.api.service;

import com.bookorange.api.domain.Course;
import com.bookorange.api.dto.courseDto.CourseCreateDTO;
import com.bookorange.api.dto.courseDto.CourseDTO;
import com.bookorange.api.dto.courseDto.CourseSectionEditDTO;

import java.util.List;

public interface CourseService {
    Course create(CourseCreateDTO courseCreateDTO);

    Course findById(Long courseId);

    List<Course> findAll();

    List<Course> findByContentType(String contentType);

    List<Course> findByCategory(String category);

    List<Course> findByDifficulty(String difficulty);

    Course update(CourseDTO courseDTO);

    void delete(Long courseId);

    void addSection(CourseSectionEditDTO courseSectionEditDTO);

    void removeSection(CourseSectionEditDTO courseSectionEditDTO);

}
