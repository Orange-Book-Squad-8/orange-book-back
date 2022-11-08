package com.bookorange.api.configuration;

import com.bookorange.api.domain.Course;
import com.bookorange.api.domain.Lesson;
import com.bookorange.api.domain.Role;
import com.bookorange.api.domain.Section;
import com.bookorange.api.enumerator.ContentType;
import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
import com.bookorange.api.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@Configuration
@EnableJpaRepositories("com.bookorange.api.repository")
@AllArgsConstructor
public class ApplicationConfiguration {

    AppUserRepository appUserRepository;
    CourseRepository courseRepository;
    LessonRepository lessonRepository;
    RoleRepository roleRepository;
    SectionRepository sectionRepository;


    @Bean
    public void initializeDatabase() {
        Lesson lesson1 = new Lesson();
        lesson1.setTitle("lessonTitle");
        lesson1.setDescription("lessonDescription");
        lesson1.setAuthor("lesson author");
        lesson1.setLink("lesson link");
        lesson1.setLink("lesson topic");
        lesson1.setContentType(ContentType.ARTICLE);
        lesson1.setDurationInMinutes(5);

        Lesson lesson2 = new Lesson();
        lesson2.setTitle("lesson2 Title");
        lesson2.setDescription("lesson2 Description");
        lesson2.setAuthor("lesson2 author");
        lesson2.setLink("lesson2 link");
        lesson2.setLink("lesson2 topic");
        lesson2.setContentType(ContentType.VIDEO);
        lesson2.setDurationInMinutes(2);

        lessonRepository.save(lesson1);
        lessonRepository.save(lesson2);


        Section section1 = new Section();
        section1.setName("section1 name");
        section1.setLessons(List.of(lesson1, lesson2));

        Section section2 = new Section();
        section2.setName("section2 name");
        section2.setLessons(List.of(lesson1, lesson2));

        sectionRepository.save(section1);
        sectionRepository.save(section2);

        Course course1 = new Course();
        course1.setTitle("Course 1 title");
        course1.setDescription("Course 1 description");
        course1.setCreator("creator 2");
        course1.setCategory(StackCategories.UI);
        course1.setDifficulty(Difficulty.ADVANCED);
        course1.setVisible(true);
        course1.setSections(List.of(section1, section2));

        courseRepository.save(course1);

        Section section3 = new Section();
        section3.setName("section3 name");
        section3.setLessons(List.of(lesson1, lesson2));

        Section section4 = new Section();
        section4.setName("section4 name");
        section4.setLessons(List.of(lesson1, lesson2));

        sectionRepository.save(section3);
        sectionRepository.save(section4);

        Course course2 = new Course();
        course2.setTitle("Course 2 title");
        course2.setDescription("Course 2 description");
        course2.setCreator("creator 2");
        course2.setCategory(StackCategories.FULLSTACK);
        course2.setDifficulty(Difficulty.BEGINNER);
        course2.setVisible(true);
        course2.setSections(List.of(section3, section4));

        courseRepository.save(course2);

        Section section5 = new Section();
        section5.setName("section5 name");
        section5.setLessons(List.of(lesson1, lesson2));

        Section section6 = new Section();
        section6.setName("section6 name");
        section6.setLessons(List.of(lesson1, lesson2));

        sectionRepository.save(section5);
        sectionRepository.save(section6);

        Course course3 = new Course();
        course3.setTitle("Course 3 title");
        course3.setDescription("Course 3 description");
        course3.setCreator("creator 3");
        course3.setCategory(StackCategories.FULLSTACK);
        course3.setDifficulty(Difficulty.BEGINNER);
        course3.setVisible(false);
        course3.setSections(List.of(section5, section6));

        courseRepository.save(course3);


        Role userRole = new Role();
        userRole.setName("user");

        Role admRole = new Role();
        admRole.setName("admin");

        roleRepository.save(userRole);
        roleRepository.save(admRole);

        /*
        AppUser user = new AppUser();
        user.setUsername("user");
        user.setPassword("password");
        user.setEmail("email@email.com");
        user.setStackCategories(List.of(StackCategories.UI, StackCategories.UX));
        user.setBadges(List.of("Java"));
        user.setRole(userRole);
        user.setSubscribedCourses(List.of(course1));

        appUserRepository.save(user);

        AppUser adm = new AppUser();
        adm.setUsername("admin");
        adm.setPassword("admin");
        adm.setEmail("admin@email.com");
        adm.setStackCategories(List.of(StackCategories.FRONT_END, StackCategories.FULLSTACK));
        adm.setBadges(List.of("Java"));
        adm.setRole(admRole);

        appUserRepository.save(adm);

         */

    }
}
