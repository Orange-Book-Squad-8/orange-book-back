package com.bookorange.api.repository;

import com.bookorange.api.domain.Course;
import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCategory(StackCategories category);

    List<Course> findByDifficulty(Difficulty difficulty);

    List<Course> findByCreator(String creator);
}
