package com.bookorange.api.service;

import com.bookorange.api.dto.lessonDto.LessonContentDTO;
import com.bookorange.api.dto.lessonDto.LessonCreateDTO;
import com.bookorange.api.dto.lessonDto.LessonDTO;
import com.bookorange.api.dto.lessonDto.LessonTopicDTO;

import java.util.List;

public interface LessonService {
    void create(LessonCreateDTO lesson);

    LessonDTO findById(Long lessonIn);

    List<LessonDTO> findAll();

    List<LessonDTO> findByTopic(LessonTopicDTO lessonTopicDTO);

    List<LessonDTO> findByContentType(LessonContentDTO lessonContentDTO);

    LessonDTO update(LessonDTO lessonDTO);

    void delete(Long lessonId);
}
