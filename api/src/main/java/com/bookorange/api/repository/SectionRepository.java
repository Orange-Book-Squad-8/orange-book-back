package com.bookorange.api.repository;

import com.bookorange.api.domain.Lesson;
import com.bookorange.api.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {

    List<Lesson> listLesson();
}
