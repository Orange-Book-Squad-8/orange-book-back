package com.bookorange.api.service.implementation;

import com.bookorange.api.domain.Course;
import com.bookorange.api.dto.courseDto.CourseCreateDTO;
import com.bookorange.api.dto.courseDto.CourseDTO;
import com.bookorange.api.dto.courseDto.CourseSectionEditDTO;
import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
import com.bookorange.api.repository.CourseRepository;
import com.bookorange.api.service.CourseService;
import com.bookorange.api.service.exceptions.ObjectNotFoundException;
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
        Course course = new Course();
        course.setTitle(courseCreateDTO.getTitle());
        course.setDescription(courseCreateDTO.getDescription());
        course.setCreator(courseCreateDTO.getCreator());
        course.setCategory(courseCreateDTO.getCategory());
        course.setDifficulty(courseCreateDTO.getDifficulty());
        course.setVisible(courseCreateDTO.getVisible());
        return courseRepository.save(course);
    }

    @Override
    public Course findById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new ObjectNotFoundException("Course not found"));
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findByCategory(StackCategories category) {
        return courseRepository.findByCategory(category);
    }

    @Override
    public List<Course> findByDifficulty(Difficulty difficulty) {
        return courseRepository.findByDifficulty(difficulty);
    }

    @Override
    public List<Course> findByCreator(String creator) {
        return courseRepository.findByCreator(creator);
    }

    @Override
    public Course update(CourseDTO courseDTO) {
        Course course = findById(courseDTO.getId());
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setCreator(courseDTO.getCreator());
        course.setCategory(courseDTO.getCategory());
        course.setDifficulty(courseDTO.getDifficulty());
        course.setVisible(courseDTO.getVisible());
        return courseRepository.save(course);
    }

    @Override
    public void delete(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public void addSection(CourseSectionEditDTO courseSectionEditDTO) {
        Course course = findById(courseSectionEditDTO.getId());
        course.addSection(courseSectionEditDTO.getSection());
        courseRepository.save(course);
    }

    @Override
    public void removeSection(CourseSectionEditDTO courseSectionEditDTO) {
        Course course = findById(courseSectionEditDTO.getId());
        course.removeSection(courseSectionEditDTO.getSection());
        courseRepository.save(course);
    }

    @Override
    public Integer getCourseDuration(Long courseId) {
        return findById(courseId).getDuration();
    }
}
