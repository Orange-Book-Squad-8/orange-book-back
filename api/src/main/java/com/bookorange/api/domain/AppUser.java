package com.bookorange.api.domain;

import com.bookorange.api.enumerator.StackCategories;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "field not found")
    @Length(min = 3, max = 30)
    private String username;

    @NotBlank(message = "field not found")
    @Length(min = 6, message = "password must contain at least 6 characters")
    private String password;

    @Column(unique = true)
    @NotBlank(message = "field not found")
    @Email(message = "email not valid")
    private String email;

    @NotNull(message = "cannot be null")
    @ElementCollection
    private List<StackCategories> stackCategories = new ArrayList<>();

    @ElementCollection
    private List<String> badges = new ArrayList<>();

    @NotNull(message = "cannot be null")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Role role;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Course> subscribedCourses = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Course> archivedCourses = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Course> myCourses = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id.equals(appUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

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
