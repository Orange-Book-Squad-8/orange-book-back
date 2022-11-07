package com.bookorange.api.domain;

import com.bookorange.api.enumerator.ContentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    private List<String> badges;

    @OneToOne(cascade = CascadeType.ALL)
    private Role role;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> subscribedCourses;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> archivedCourses;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> myCourses;

    public void addSubscribedCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        if (subscribedCourses.contains(course)) throw new IllegalStateException("course already subscribed");
        getSubscribedCourses().add(course);
    }

    public void removeSubscribedCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        if (!subscribedCourses.contains(course)) throw new IllegalStateException("course not subscribed yet");
        getSubscribedCourses().remove(course);
    }

    public void addArchivedCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        if (archivedCourses.contains(course)) throw new IllegalStateException("course already archived");
        getArchivedCourses().add(course);
    }

    public void removeArchivedCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        if (!archivedCourses.contains(course)) throw new IllegalStateException("course not archived yet");
        getArchivedCourses().remove(course);
    }

    public void addMyCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        if (!myCourses.contains(course)) throw new IllegalStateException("course already created");
        getMyCourses().add(course);
    }

    public void removeMyCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        if (!myCourses.contains(course)) throw new IllegalStateException("course does not exist");
        getMyCourses().remove(course);
    }

    public void finishCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        if (!subscribedCourses.contains(course)) throw new IllegalStateException("course not subscribed");
        removeSubscribedCourse(course);
        addArchivedCourse(course);
    }

    public void addBadge(String badge) {
        if (badge == null) throw new IllegalArgumentException("badge cannot be null");
        if (badges.contains(badge)) throw new IllegalArgumentException("badge already exists");
        badges.add(badge);
    }

    public void removeBadge(String badge) {
        if (badge == null) throw new IllegalArgumentException("badge cannot be null");
        if (!badges.contains(badge)) throw new IllegalArgumentException("badge does not exist");
        badges.remove(badge);
    }
}
