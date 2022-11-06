package com.bookorange.api.repository;

import com.bookorange.api.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    //List<Lesson> listByTopic(String name);

    //List<Lesson> listByContentType(String name);
}
