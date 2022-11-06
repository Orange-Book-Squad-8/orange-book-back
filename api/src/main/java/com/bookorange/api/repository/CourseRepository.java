package com.bookorange.api.repository;

import com.bookorange.api.domain.Course;
import com.bookorange.api.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {

    List<Course> listByContentType(String contentType);

    List<Course> listByCategory(String category);

    List<Course> listByDifficulty(String difficulty);

    List<Section> listSection();
}
