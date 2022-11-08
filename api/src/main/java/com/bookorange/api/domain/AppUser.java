package com.bookorange.api.domain;

import com.bookorange.api.enumerator.StackCategories;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String email;

    @ElementCollection
    private List<StackCategories> stackCategories = new ArrayList<>();

    @ElementCollection
    private List<String> badges = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Role role;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> subscribedCourses = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> archivedCourses = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> myCourses = new ArrayList<>();

    public void addSubscribedCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        if (subscribedCourses.contains(course)) throw new IllegalArgumentException("course already subscribed");
        subscribedCourses.add(course);
    }

    public void removeSubscribedCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        if (!subscribedCourses.contains(course)) throw new IllegalArgumentException("course not subscribed yet");
        getSubscribedCourses().remove(course);
    }

    public void addArchivedCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        if (archivedCourses.contains(course)) throw new IllegalArgumentException("course already archived");
        archivedCourses.add(course);
    }

    public void removeArchivedCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        if (!archivedCourses.contains(course)) throw new IllegalArgumentException("course not archived yet");
        archivedCourses.remove(course);
    }

    public void addMyCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        if (myCourses.contains(course)) throw new IllegalArgumentException("course already created");
        myCourses.add(course);
    }

    public void removeMyCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        if (!myCourses.contains(course)) throw new IllegalArgumentException("course does not exist");
        myCourses.remove(course);
    }

    public void finishCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course cannot be null");
        if (!subscribedCourses.contains(course)) throw new IllegalArgumentException("course not subscribed");
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

    public void addStackCategory(StackCategories stackCategory) {
        if (stackCategory == null) throw new IllegalArgumentException("stackCategory cannot be null");
        if (stackCategories.contains(stackCategory)) throw new IllegalArgumentException("stackCategory already exists");
        stackCategories.add(stackCategory);
    }

    public void removeStackCategory(StackCategories stackCategory) {
        if (stackCategory == null) throw new IllegalArgumentException("stackCategory cannot be null");
        if (!stackCategories.contains(stackCategory))
            throw new IllegalArgumentException("stackCategory does not exist");
        stackCategories.remove(stackCategory);
    }
}
