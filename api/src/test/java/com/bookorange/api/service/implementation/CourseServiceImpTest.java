package com.bookorange.api.service.implementation;

import com.bookorange.api.domain.Course;
import com.bookorange.api.domain.Lesson;
import com.bookorange.api.domain.Section;
import com.bookorange.api.dto.courseDto.CourseCreateDTO;
import com.bookorange.api.dto.courseDto.CourseDTO;
import com.bookorange.api.dto.courseDto.CourseSectionEditDTO;
import com.bookorange.api.enumerator.ContentType;
import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
import com.bookorange.api.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CourseServiceImpTest {


    @Mock
    CourseRepository courseRepository;

    @InjectMocks
    CourseServiceImp courseServiceImp;

    @Test
    void create() {
        CourseCreateDTO course = new CourseCreateDTO();

        courseServiceImp.create(course);
        Mockito.verify(courseRepository, Mockito.times(1)).save(Mockito.any(Course.class));
    }

    @Test
    void findById() {
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(new Course()));
        courseServiceImp.findById(1L);
        Mockito.verify(courseRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void findByIdShouldThrowWhenLessonDoesNotExists() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> courseServiceImp.findById(1L));
        assertEquals("Course not found", exception.getMessage());
    }

    @Test
    void findAll() {
        courseServiceImp.findAll();
        Mockito.verify(courseRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findByCategory() {
        List<Course> lessonTopics = courseServiceImp.findByCategory(StackCategories.UI);
        Mockito.verify(courseRepository, Mockito.times(1)).findByCategory(Mockito.any());
        assertEquals(0, lessonTopics.size());
    }

    @Test
    void findByDifficulty() {
        List<Course> lessons = courseServiceImp.findByDifficulty(Difficulty.ADVANCED);
        Mockito.verify(courseRepository, Mockito.times(1)).findByDifficulty(Mockito.any());
        assertEquals(0, lessons.size());
    }

    @Test
    void update() {
        Mockito.when(courseRepository.findById(Mockito.any())).thenReturn(Optional.of(new Course()));
        courseServiceImp.update(new CourseDTO());
        Mockito.verify(courseRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void delete() {
        courseServiceImp.delete(1L);
        Mockito.verify(courseRepository, Mockito.times(1)).deleteById(1L);
    }


    @Test
    void addSection() {
        CourseSectionEditDTO dto = new CourseSectionEditDTO();
        dto.setSection(new Section());
        Mockito.when(courseRepository.findById(Mockito.any())).thenReturn(Optional.of(new Course()));
        courseServiceImp.addSection(dto);
        Mockito.verify(courseRepository, Mockito.times(1)).save(Mockito.any(Course.class));
    }

    @Test
    void removeSection() {
        Section section = new Section();
        CourseSectionEditDTO dto = new CourseSectionEditDTO();
        dto.setSection(section);
        Course course = new Course();
        course.addSection(section);

        Mockito.when(courseRepository.findById(Mockito.any())).thenReturn(Optional.of(course));
        courseServiceImp.removeSection(dto);
        Mockito.verify(courseRepository, Mockito.times(1)).save(Mockito.any(Course.class));
    }

    @Test
    void getCourseDuration() {
        Lesson lesson = new Lesson(
                1L,
                "lessonTitle",
                "lessonDescription",
                "lesson author",
                "lesson link",
                "lesson topic",
                ContentType.ARTICLE,
                5);

        Lesson lesson2 = new Lesson(
                2L,
                "lessonTitle2",
                "lessonDescription2",
                "lesson author2",
                "lesson link2",
                "lesson topic2",
                ContentType.BOOK,
                5);
        Section section1 = new Section(
                3L,
                "sectionName",
                new ArrayList<>(Arrays.asList(lesson, lesson2)));
        Section section2 = new Section(
                4L,
                "sectionName",
                new ArrayList<>(Arrays.asList(lesson, lesson2)));
        Course course = new Course();
        course.setSections(Arrays.asList(section1, section2));
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(course));

        Integer duration = courseServiceImp.getCourseDuration(1L);

        assertEquals(section1.getDuration() + section2.getDuration(), duration);

    }
}