package com.bookorange.api.service;

import com.bookorange.api.dto.courseDto.*;

import java.util.List;

public interface CourseService {
    CourseDTO create(CourseCreateDTO courseCreateDTO);

    CourseDTO findById(Long courseId);

    List<CourseDTO> findAll();

    List<CourseDTO> findByContentType(CourseContentDTO courseContentDTO);

    List<CourseDTO> findByCategory(CourseCategoryDTO courseCategoryDTO);

    List<CourseDTO> findByDifficulty(CourseDifficultyDTO courseDifficultyDTO);

    CourseDTO update(CourseDTO courseDTO);

    void delete(Long courseId);

    void addSection(CourseSectionDTO courseSectionDTO);

    void removeSection(CourseSectionDTO courseSectionDTO);

}
