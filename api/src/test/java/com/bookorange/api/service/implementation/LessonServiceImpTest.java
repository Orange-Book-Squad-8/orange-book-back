package com.bookorange.api.service.implementation;

import com.bookorange.api.domain.Lesson;
import com.bookorange.api.dto.lessonDto.LessonCreateDTO;
import com.bookorange.api.dto.lessonDto.LessonDTO;
import com.bookorange.api.enumerator.ContentType;
import com.bookorange.api.repository.LessonRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class LessonServiceImpTest {

    @Mock
    LessonRepository lessonRepository;

    @InjectMocks
    LessonServiceImp lessonServiceImp;

    @Test
    void create() {
        LessonCreateDTO lesson = new LessonCreateDTO();

        lessonServiceImp.create(lesson);
        ArgumentCaptor<Lesson> argument = ArgumentCaptor.forClass(Lesson.class);
        Mockito.verify(lessonRepository, Mockito.times(1)).save(argument.capture());
    }

    @Test
    void findById() {
        Mockito.when(lessonRepository.findById(1L)).thenReturn(Optional.of(new Lesson()));
        lessonServiceImp.findById(1L);
        Mockito.verify(lessonRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void findByIdShouldThrowWhenLessonDoesNotExists() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> lessonServiceImp.findById(1L));
        assertEquals("Couldn't find lesson", exception.getMessage());
    }

    @Test
    void findAll() {
        lessonServiceImp.findAll();
        Mockito.verify(lessonRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findByTopic() {
        List<Lesson> lessonTopics = lessonServiceImp.findByTopic("topic");
        Mockito.verify(lessonRepository, Mockito.times(1));
        assertEquals(0, lessonTopics.size());
    }

    @Test
    void findByContentType() {
        List<Lesson> lessons = lessonServiceImp.findByContentType(ContentType.ARTICLE);
        Mockito.verify(lessonRepository, Mockito.times(1)).findByContentType(ContentType.ARTICLE);
        assertEquals(0, lessons.size());
    }

    @Test
    void update() {
        Mockito.when(lessonRepository.findById(1L)).thenReturn(Optional.of(new Lesson()));
        LessonDTO lesson = new LessonDTO();
        lesson.setId(1L);
        lessonServiceImp.update(lesson);
        ArgumentCaptor<Lesson> argument = ArgumentCaptor.forClass(Lesson.class);
        Mockito.verify(lessonRepository, Mockito.times(1)).save(argument.capture());
    }

    @Test
    void delete() {
        lessonServiceImp.delete(1L);
        Mockito.verify(lessonRepository, Mockito.times(1)).deleteById(1L);
    }
}