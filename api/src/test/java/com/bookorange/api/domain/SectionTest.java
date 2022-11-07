package com.bookorange.api.domain;

import com.bookorange.api.enumerator.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class SectionTest {

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

    @Test
    void shouldAddLesson() {
        Section section = new Section();
        section.addLesson(lesson);
        assertTrue(section.getLessons().contains(lesson));
    }

    @Test
    void shouldThrowErrorWhenAddInappropriateLesson() {
        Section section = new Section();
        section.addLesson(lesson);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> section.addLesson(null));
        assertEquals("lesson cannot be null", illegalArgumentException.getMessage());

        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> section.addLesson(lesson));
        assertEquals("lesson already exists", illegalArgumentException.getMessage());
    }

    @Test
    void shouldRemoveLesson() {
        Section section = new Section();
        section.addLesson(lesson);
        section.removeLesson(lesson);
        assertFalse(section.getLessons().contains(lesson));
    }

    @Test
    void shouldThrowErrorWhenRemoveInappropriateLesson() {
        Section section = new Section();

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> section.removeLesson(null));
        assertEquals("lesson cannot be null", illegalArgumentException.getMessage());

        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> section.removeLesson(lesson));
        assertEquals("lesson does not exist", illegalArgumentException.getMessage());
    }

    @Test
    void shouldGetDuration() {
        Section section = new Section();
        section.addLesson(lesson);
        section.addLesson(lesson2);
        assertEquals(lesson.getDurationInMinutes() + lesson2.getDurationInMinutes(), section.getDuration());
    }

    @Test
    void shouldGetId() {
        Section section = new Section();
        section.setId(1L);
        assertEquals(1L, section.getId());
    }

    @Test
    void getName() {
        Section section = new Section();
        section.setName("sectionName");
        assertEquals("sectionName", section.getName());
    }

    @Test
    void shouldConstructSections() throws NoSuchMethodException {
        Section section = new Section(
                1l,
                "sectionName",
                new ArrayList<>()
        );
        assertEquals(1L, section.getId());
        assertEquals("sectionName", section.getName());
        assertEquals(ArrayList.class, section.getLessons().getClass());
    }
}