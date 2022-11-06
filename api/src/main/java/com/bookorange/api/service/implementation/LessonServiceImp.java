package com.bookorange.api.service.implementation;


import com.bookorange.api.domain.Lesson;
import com.bookorange.api.dto.lessonDto.LessonCreateDTO;
import com.bookorange.api.dto.lessonDto.LessonDTO;
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
    public Lesson create(LessonCreateDTO lesson) {
        return null;
    }

    @Override
    public Lesson findById(Long lessonId) {
        return null;
    }

    @Override
    public List<Lesson> findAll() {
        return null;
    }

    @Override
    public List<Lesson> findByTopic(String topic) {
        return null;
    }

    @Override
    public List<Lesson> findByContentType(String contentType) {
        return null;
    }

    @Override
    public Lesson update(LessonDTO lessonDTO) {
        return null;
    }

    @Override
    public void delete(Long lessonId) {

    }
}
