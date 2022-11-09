package com.bookorange.api.service.implementation;

import com.bookorange.api.domain.Lesson;
import com.bookorange.api.domain.Section;
import com.bookorange.api.dto.sectionDto.SectionCreateDTO;
import com.bookorange.api.dto.sectionDto.SectionDTO;
import com.bookorange.api.dto.sectionDto.SectionEditLessonDTO;
import com.bookorange.api.repository.SectionRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class SectionServiceImpTest {

    @Mock
    SectionRepository sectionRepository;
    @InjectMocks
    SectionServiceImp sectionServiceImp;

    @Test
    void create() {
        sectionServiceImp.create(new SectionCreateDTO());
        Mockito.verify(sectionRepository, Mockito.times(1)).save(Mockito.any(Section.class));
    }

    @Test
    void update() {
        Mockito.when(sectionRepository.findById(Mockito.any())).thenReturn(Optional.of(new Section()));
        sectionServiceImp.update(new SectionDTO());
        Mockito.verify(sectionRepository, Mockito.times(1)).save(Mockito.any(Section.class));
    }

    @Test
    void delete() {
        sectionServiceImp.delete(1L);
        Mockito.verify(sectionRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    void addLesson() {
        SectionEditLessonDTO sectionDTO = new SectionEditLessonDTO();
        sectionDTO.setLesson(new Lesson());
        Mockito.when(sectionRepository.findById(Mockito.any())).thenReturn(Optional.of(new Section()));
        sectionServiceImp.addLesson(sectionDTO);
        Mockito.verify(sectionRepository, Mockito.times(1)).save(Mockito.any(Section.class));
    }

    @Test
    void removeLesson() {
        Lesson lesson = new Lesson();
        SectionEditLessonDTO sectionDTO = new SectionEditLessonDTO();
        sectionDTO.setLesson(lesson);
        Section section = new Section();
        section.addLesson(lesson);

        Mockito.when(sectionRepository.findById(Mockito.any())).thenReturn(Optional.of(section));
        sectionServiceImp.removeLesson(sectionDTO);
        Mockito.verify(sectionRepository, Mockito.times(1)).save(Mockito.any(Section.class));
    }

    @Test
    void findById() {
        Mockito.when(sectionRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Section()));
        sectionServiceImp.findById(1L);
        Mockito.verify(sectionRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    void findByIdShouldThrowWhenSectionDoesNotExists() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> sectionServiceImp.findById(1L));
        assertEquals("Could not find section", exception.getMessage());
    }
}