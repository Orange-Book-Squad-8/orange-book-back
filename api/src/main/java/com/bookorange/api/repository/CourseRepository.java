package com.bookorange.api.repository;

import com.bookorange.api.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
/*
    List<Course> listByContentType(String contentType);

    List<Course> listByCategory(String category);

    List<Course> listByDifficulty(String difficulty);*/
}
