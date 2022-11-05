package com.bookorange.api.domain;

import com.bookorange.api.enumerator.ContentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String email;

    private ContentType contentType;

    @ElementCollection
    private Set<String> badges;

    @OneToOne(cascade = CascadeType.ALL)
    private Role role;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> subscribedCourses;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> archivedCourses;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> myCourses;

    public void addSubscribedCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        getSubscribedCourses().add(course);
    }

    public void removeSubscribedCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        getSubscribedCourses().remove(course);
    }

    public void addArchivedCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        getArchivedCourses().add(course);
    }

    public void removeArchivedCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        getArchivedCourses().remove(course);
    }

    public void addMyCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        getMyCourses().add(course);
    }

    public void removeMyCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        getMyCourses().remove(course);
    }

    public void finishCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        removeSubscribedCourse(course);
        addArchivedCourse(course);
    }

    public void addBadge(String badge) {
        if (badge == null) throw new IllegalArgumentException("badge cannot be null");
        badges.add(badge);
    }

    public void removeBadge(String badge) {
        if (badge == null) throw new IllegalArgumentException("badge cannot be null");
        badges.remove(badge);
    }
}
