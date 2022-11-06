package com.bookorange.api.service.implementation;

import com.bookorange.api.domain.Course;
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
    public Course create(CourseCreateDTO courseCreateDTO) {
        return null;
    }

    @Override
    public Course findById(Long courseId) {
        return null;
    }

    @Override
    public List<Course> findAll() {
        return null;
    }

    @Override
    public List<Course> findByContentType(String contentType) {
        return null;
    }

    @Override
    public List<Course> findByCategory(String category) {
        return null;
    }

    @Override
    public List<Course> findByDifficulty(String difficulty) {
        return null;
    }

    @Override
    public Course update(CourseDTO courseDTO) {
        return null;
    }

    @Override
    public void delete(Long courseId) {

    }

    @Override
    public void addSection(CourseSectionEditDTO courseSectionEditDTO) {

    }

    @Override
    public void removeSection(CourseSectionEditDTO courseSectionEditDTO) {

    }
}
