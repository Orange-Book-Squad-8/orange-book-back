package com.bookorange.api.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;


@Entity
@Getter
@Setter
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;

    @JsonIgnore
    private ArrayList<Course> coursesSubscribed;

    @JsonIgnore
    private ArrayList<Course> coursesArchived;


    public void addCourseSubscribed(Course course) {
        getCoursesSubscribed().add(course);
    }

    public void removeCourseSubscribed(Course course) {
        getCoursesSubscribed().remove(course);
    }

    public void addCourseArchived(Course course) {
        getCoursesArchived().add(course);
    }

    public void removeCourseArchived(Course course) {
        getCoursesArchived().remove(course);
    }

    public void finishCourse(Course course){
        removeCourseSubscribed(course);
        addCourseArchived(course);
    }


}
