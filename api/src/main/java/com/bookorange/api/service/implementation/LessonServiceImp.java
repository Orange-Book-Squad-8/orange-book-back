package com.bookorange.api.service.implementation;


import com.bookorange.api.dto.lessonDto.LessonContentDTO;
import com.bookorange.api.dto.lessonDto.LessonCreateDTO;
import com.bookorange.api.dto.lessonDto.LessonDTO;
import com.bookorange.api.dto.lessonDto.LessonTopicDTO;
import com.bookorange.api.repository.LessonRepository;
import com.bookorange.api.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImp implements LessonService {

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImp(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void create(LessonCreateDTO lesson) {

    }

    @Override
    public LessonDTO findById(Long lessonIn) {
        return null;
    }

    @Override
    public List<LessonDTO> findAll() {
        return null;
    }

    @Override
    public List<LessonDTO> findByTopic(LessonTopicDTO lessonTopicDTO) {
        return null;
    }

    @Override
    public List<LessonDTO> findByContentType(LessonContentDTO lessonContentDTO) {
        return null;
    }

    @Override
    public LessonDTO update(LessonDTO lessonDTO) {
        return null;
    }

    @Override
    public void delete(Long lessonId) {

    }
}
