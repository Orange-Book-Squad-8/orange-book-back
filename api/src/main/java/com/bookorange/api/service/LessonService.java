package com.bookorange.api.service;

import com.bookorange.api.domain.Lesson;
import com.bookorange.api.dto.lessonDto.LessonCreateDTO;
import com.bookorange.api.dto.lessonDto.LessonDTO;
import com.bookorange.api.enumerator.ContentType;

import java.util.List;

public interface LessonService {
    Lesson create(LessonCreateDTO lessonDTO);

    Lesson findById(Long lessonId);

    List<Lesson> findAll();

    List<Lesson> findByTopic(String topic);

    List<Lesson> findByContentType(ContentType contentType);

    Lesson update(LessonDTO lessonDTO);

    void delete(Long lessonId);
}
