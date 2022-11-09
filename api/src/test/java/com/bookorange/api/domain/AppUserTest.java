package com.bookorange.api.domain;

import com.bookorange.api.enumerator.StackCategories;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppUserTest {
    Course course = new Course();

    @Test
    void shouldAddSubscribedCourse() {
        AppUser appUser = new AppUser();
        appUser.addSubscribedCourse(course);
        assertTrue(appUser.getSubscribedCourses().contains(course));
    }

    @Test
    void shouldThrowErrorWhenAddInappropriateCourseToSubscribed() {
        AppUser appUser = new AppUser();
        appUser.setSubscribedCourses(List.of(course));

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.addSubscribedCourse(null));
        assertEquals("course cannot be null", illegalArgumentException.getMessage());

        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.addSubscribedCourse(course));
        assertEquals("course already subscribed", illegalArgumentException.getMessage());
    }

    @Test
    void shouldRemoveSubscribedCourse() {
        AppUser appUser = new AppUser();
        appUser.addSubscribedCourse(course);
        appUser.removeSubscribedCourse(course);
        assertFalse(appUser.getSubscribedCourses().contains(course));
    }

    @Test
    void shouldThrowErrorWhenRemoveInappropriateCourseToSubscribed() {
        AppUser appUser = new AppUser();

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.removeSubscribedCourse(null));
        assertEquals("course cannot be null", illegalArgumentException.getMessage());

        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.removeSubscribedCourse(course));
        assertEquals("course not subscribed yet", illegalArgumentException.getMessage());
    }

    @Test
    void shouldAddArchivedCourse() {
        AppUser appUser = new AppUser();
        appUser.addArchivedCourse(course);
        assertTrue(appUser.getArchivedCourses().contains(course));
    }

    @Test
    void shouldThrowErrorWhenAddInappropriateCourseToArchived() {
        AppUser appUser = new AppUser();
        appUser.setArchivedCourses(List.of(course));

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.addArchivedCourse(null));
        assertEquals("course cannot be null", illegalArgumentException.getMessage());

        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.addArchivedCourse(course));
        assertEquals("course already archived", illegalArgumentException.getMessage());
    }

    @Test
    void shouldRemoveArchivedCourse() {
        AppUser appUser = new AppUser();
        appUser.addArchivedCourse(course);
        appUser.removeArchivedCourse(course);
        assertFalse(appUser.getArchivedCourses().contains(course));
    }

    @Test
    void shouldThrowErrorWhenRemoveInappropriateCourseToArchived() {
        AppUser appUser = new AppUser();

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.removeArchivedCourse(null));
        assertEquals("course cannot be null", illegalArgumentException.getMessage());

        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.removeArchivedCourse(course));
        assertEquals("course not archived yet", illegalArgumentException.getMessage());
    }

    @Test
    void shouldAddMyCourse() {
        AppUser appUser = new AppUser();
        appUser.addMyCourse(course);
        assertTrue(appUser.getMyCourses().contains(course));
    }

    @Test
    void shouldThrowErrorWhenAddInappropriateCourseToMyCourse() {
        AppUser appUser = new AppUser();
        appUser.setMyCourses(List.of(course));

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.addMyCourse(null));
        assertEquals("course cannot be null", illegalArgumentException.getMessage());

        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.addMyCourse(course));
        assertEquals("course already created", illegalArgumentException.getMessage());
    }

    @Test
    void shouldRemoveMyCourse() {
        AppUser appUser = new AppUser();
        appUser.addMyCourse(course);
        appUser.removeMyCourse(course);
        assertFalse(appUser.getMyCourses().contains(course));
    }

    @Test
    void shouldThrowErrorWhenRemoveInappropriateCourseToMyCourse() {
        AppUser appUser = new AppUser();

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.removeMyCourse(null));
        assertEquals("course cannot be null", illegalArgumentException.getMessage());

        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.removeMyCourse(course));
        assertEquals("course does not exist", illegalArgumentException.getMessage());
    }


    @Test
    void finishCourse() {
        AppUser appUser = new AppUser();
        appUser.addSubscribedCourse(course);
        appUser.finishCourse(course);

        assertFalse(appUser.getSubscribedCourses().contains(course));
        assertTrue(appUser.getArchivedCourses().contains(course));
    }

    @Test
    void shouldThrowErrorFinishInappropriateCourse() {
        AppUser appUser = new AppUser();

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.finishCourse(null));
        assertEquals("course cannot be null", illegalArgumentException.getMessage());

        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.finishCourse(course));
        assertEquals("course not subscribed", illegalArgumentException.getMessage());
    }

    @Test
    void shouldAddBadge() {
        AppUser appUser = new AppUser();
        appUser.addBadge("badge");
        assertTrue(appUser.getBadges().contains("badge"));
    }

    @Test
    void shouldThrowErrorWhenAddInappropriateBadge() {
        AppUser appUser = new AppUser();
        appUser.setBadges(List.of("badge"));

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.addBadge(null));
        assertEquals("badge cannot be null", illegalArgumentException.getMessage());

        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.addBadge("badge"));
        assertEquals("badge already exists", illegalArgumentException.getMessage());
    }

    @Test
    void shouldRemoveBadge() {
        AppUser appUser = new AppUser();
        appUser.addBadge("badge");
        appUser.removeBadge("badge");
        assertFalse(appUser.getBadges().contains("badge"));
    }

    @Test
    void shouldThrowErrorWhenRemoveInappropriateBadge() {
        AppUser appUser = new AppUser();

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.removeBadge(null));
        assertEquals("badge cannot be null", illegalArgumentException.getMessage());

        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.removeBadge("badge"));
        assertEquals("badge does not exist", illegalArgumentException.getMessage());
    }

    @Test
    void shouldAddStack() {
        AppUser appUser = new AppUser();
        appUser.setStackCategories(List.of(StackCategories.UI));
        assertTrue(appUser.getStackCategories().contains(StackCategories.UI));
    }

    @Test
    void shouldThrowErrorWhenAddInappropriateStack() {
        AppUser appUser = new AppUser();
        appUser.setStackCategories(List.of(StackCategories.UI));

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.addStackCategory(null));
        assertEquals("stackCategory cannot be null", illegalArgumentException.getMessage());

        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.addStackCategory(StackCategories.UI));
        assertEquals("stackCategory already exists", illegalArgumentException.getMessage());
    }

    @Test
    void shouldRemoveStack() {
        AppUser appUser = new AppUser();
        appUser.addStackCategory(StackCategories.UI);
        appUser.removeStackCategory(StackCategories.UI);
        assertFalse(appUser.getStackCategories().contains(StackCategories.UI));
    }

    @Test
    void shouldThrowErrorWhenRemoveInappropriateStack() {
        AppUser appUser = new AppUser();

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.removeStackCategory(null));
        assertEquals("stackCategory cannot be null", illegalArgumentException.getMessage());

        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> appUser.removeStackCategory(StackCategories.UI));
        assertEquals("stackCategory does not exist", illegalArgumentException.getMessage());
    }

    @Test
    void getId() {
        AppUser appUser = new AppUser();
        appUser.setId(1L);
        assertEquals(1l, appUser.getId());
    }

    @Test
    void getUsername() {
        AppUser appUser = new AppUser();
        appUser.setUsername("username");
        assertEquals("username", appUser.getUsername());
    }

    @Test
    void getPassword() {
        AppUser appUser = new AppUser();
        appUser.setPassword("password");
        assertEquals("password", appUser.getPassword());
    }

    @Test
    void getEmail() {
        AppUser appUser = new AppUser();
        appUser.setEmail("email");
        assertEquals("email", appUser.getEmail());
    }


    @Test
    void getRole() {
        AppUser appUser = new AppUser();
        Role role = new Role();
        appUser.setRole(role);
        assertEquals(role, appUser.getRole());
    }

    @Test
    void shouldConstructAppUser() {
        AppUser appUser = new AppUser(
                1L,
                "username",
                "password",
                "email",
                new ArrayList<>(),
                new ArrayList<>(),
                new Role(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        assertEquals(AppUser.class, appUser.getClass());
    }
}