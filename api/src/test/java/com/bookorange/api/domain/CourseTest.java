package com.bookorange.api.domain;

import com.bookorange.api.enumerator.ContentType;
import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
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

    @Test
    void shouldGetDuration() {
        Course course = new Course();
        course.setSections(Arrays.asList(section1, section2));
        assertEquals(section1.getDuration() + section2.getDuration(), course.getDuration());
    }

    @Test
    void shouldAddSection() {
        Course course = new Course();
        course.addSection(section1);
        assertTrue(course.getSections().contains(section1));
    }

    @Test
    void shouldThrowErrorWhenAddInappropriateSection() {
        Course course = new Course();
        course.setSections(List.of(section1));

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> course.addSection(null));
        assertEquals("section cannot be null", illegalArgumentException.getMessage());

        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> course.addSection(section1));
        assertEquals("section already exists", illegalArgumentException.getMessage());
    }

    @Test
    void shouldRemoveSection() {
        Course course = new Course();
        course.addSection(section1);
        course.removeSection(section1);
        assertFalse(course.getSections().contains(section1));
    }

    @Test
    void shouldThrowErrorWhenRemoveInappropriateSection() {
        Course course = new Course();

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> course.removeSection(null));
        assertEquals("section cannot be null", illegalArgumentException.getMessage());

        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> course.removeSection(section1));
        assertEquals("section does not exist", illegalArgumentException.getMessage());
    }

    @Test
    void shouldGetId() {
        Course course = new Course();
        course.setId(6L);
        assertEquals(6L, course.getId());
    }

    @Test
    void shouldGetTitle() {
        Course course = new Course();
        course.setTitle("title");
        assertEquals("title", course.getTitle());
    }

    @Test
    void shouldSetDescription() {
        Course course = new Course();
        course.setDescription("description");
        assertEquals("description", course.getDescription());
    }

    @Test
    void shouldGetCreator() {
        Course course = new Course();
        course.setCreator("creator");
        assertEquals("creator", course.getCreator());
    }

    @Test
    void shouldGetCategory() {
        Course course = new Course();
        course.setCategory(StackCategories.UI);
        assertEquals(StackCategories.UI, course.getCategory());
    }


    @Test
    void shouldGetDifficulty() {
        Course course = new Course();
        course.setDifficulty(Difficulty.ADVANCED);
        assertEquals(Difficulty.ADVANCED, course.getDifficulty());
    }

    @Test
    void shouldGetVisible() {
        Course course = new Course();
        course.setVisible(false);
        assertFalse(course.getVisible());
    }

    @Test
    void shouldConstructCourse() {
        Course course = new Course(
                5L,
                "title",
                "description",
                "creator",
                StackCategories.UI,
                Difficulty.ADVANCED,
                false,
                new ArrayList<>(),
                new ArrayList<>()
        );
        assertEquals(5L, course.getId());
        assertEquals("title", course.getTitle());
        assertEquals("description", course.getDescription());
        assertEquals("creator", course.getCreator());
        assertEquals(StackCategories.UI, course.getCategory());
        assertEquals(Difficulty.ADVANCED, course.getDifficulty());
        assertFalse(course.getVisible());
        assertEquals(ArrayList.class, course.getSections().getClass());
        assertEquals(ArrayList.class, course.getIdSections());
    }
}