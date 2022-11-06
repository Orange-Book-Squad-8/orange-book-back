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
    public Lesson create(LessonCreateDTO lessonDTO) {
        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setDescription(lessonDTO.getDescription());
        lesson.setAuthor(lessonDTO.getAuthor());
        lesson.setLink(lessonDTO.getLink());
        lesson.setTopic(lessonDTO.getTopic());
        lesson.setContentType(lessonDTO.getContentType());
        lesson.setDurationInMinutes(lessonDTO.getDurationInMinutes());
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson findById(Long lessonId) {
        return lessonRepository.findById(lessonId).orElseThrow(() -> new RuntimeException("Couldn't find lesson"));
    }

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    @Override
    public List<Lesson> findByTopic(String topic) {
        return lessonRepository.listByTopic(topic);
    }

    @Override
    public List<Lesson> findByContentType(String contentType) {
        return lessonRepository.listByContentType(contentType);
    }

    @Override
    public Lesson update(LessonDTO lessonDTO) {
        Lesson lesson = findById(lessonDTO.getId());
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setDescription(lessonDTO.getDescription());
        lesson.setAuthor(lessonDTO.getAuthor());
        lesson.setLink(lessonDTO.getLink());
        lesson.setTopic(lessonDTO.getTopic());
        lesson.setContentType(lessonDTO.getContentType());
        lesson.setDurationInMinutes(lessonDTO.getDurationInMinutes());
        return lessonRepository.save(lesson);
    }

    @Override
    public void delete(Long lessonId) {
        lessonRepository.deleteById(lessonId);
    }
}
