package com.bookorange.api.service;

import com.bookorange.api.domain.Lesson;
import com.bookorange.api.dto.lessonDto.LessonCreateDTO;
import com.bookorange.api.dto.lessonDto.LessonDTO;

import java.util.List;

public interface LessonService {
    Lesson create(LessonCreateDTO lesson);

    Lesson findById(Long lessonId);

    List<Lesson> findAll();

    List<Lesson> findByTopic(String topic);

    List<Lesson> findByContentType(String contentType);

    Lesson update(LessonDTO lessonDTO);

    void delete(Long lessonId);
}
