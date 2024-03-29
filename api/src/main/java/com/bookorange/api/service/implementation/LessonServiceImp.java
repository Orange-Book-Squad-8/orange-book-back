package com.bookorange.api.service.implementation;


import com.bookorange.api.domain.Lesson;
import com.bookorange.api.dto.lessonDto.LessonCreateDTO;
import com.bookorange.api.dto.lessonDto.LessonDTO;
import com.bookorange.api.enumerator.ContentType;
import com.bookorange.api.repository.LessonRepository;
import com.bookorange.api.service.LessonService;
import com.bookorange.api.handler.exception.ObjectNotFoundException;
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
        return lessonRepository.findById(lessonId).orElseThrow(() -> new ObjectNotFoundException("Not found lesson"));
    }

    @Override
    public List<Lesson> findAll() {
        List<Lesson> list = lessonRepository.findAll();
        if(list.isEmpty()) throw new ObjectNotFoundException("List empty");
        return list;
    }

    @Override
    public List<Lesson> findByTopic(String topic) {
        List<Lesson> list = lessonRepository.findByTopic(topic);
        if(list.isEmpty()) throw new ObjectNotFoundException("Not found lessons for topic: " + topic);
        return list;
    }

    @Override
    public List<Lesson> findByContentType(ContentType contentType) {
        List<Lesson> list = lessonRepository.findByContentType(contentType);
        if(list.isEmpty()) throw new ObjectNotFoundException("Not found lesson for content type: " + contentType);
        return list;
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
