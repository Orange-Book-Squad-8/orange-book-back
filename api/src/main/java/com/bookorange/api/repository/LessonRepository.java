package com.bookorange.api.repository;

import com.bookorange.api.domain.Lesson;
import com.bookorange.api.enumerator.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByTopic(String name);

    List<Lesson> findByContentType(ContentType contentType);
}
