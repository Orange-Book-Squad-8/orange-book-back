package com.bookorange.api.service.implementation;

import com.bookorange.api.dto.courseDto.*;
import com.bookorange.api.repository.CourseRepository;
import com.bookorange.api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImp(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseDTO create(CourseCreateDTO courseCreateDTO) {
        return null;
    }

    @Override
    public CourseDTO findById(Long courseId) {
        return null;
    }

    @Override
    public List<CourseDTO> findAll() {
        return null;
    }

    @Override
    public List<CourseDTO> findByContentType(CourseContentDTO courseContentDTO) {
        return null;
    }

    @Override
    public List<CourseDTO> findByCategory(CourseCategoryDTO courseCategoryDTO) {
        return null;
    }

    @Override
    public List<CourseDTO> findByDifficulty(CourseDifficultyDTO courseDifficultyDTO) {
        return null;
    }

    @Override
    public CourseDTO update(CourseDTO courseDTO) {
        return null;
    }

    @Override
    public void delete(Long courseId) {

    }

    @Override
    public void addSection(CourseSectionDTO courseSectionDTO) {

    }

    @Override
    public void removeSection(CourseSectionDTO courseSectionDTO) {

    }
}
