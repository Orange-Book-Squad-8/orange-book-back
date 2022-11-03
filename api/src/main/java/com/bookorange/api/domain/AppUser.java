package com.bookorange.api.domain;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Role role;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> subscribedCourses;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> archivedCourses;

    public void addSubscribedCourse(Course course) {
        getSubscribedCourses().add(course);
    }

    public void removeSubscribedCourse(Course course) {
        getSubscribedCourses().remove(course);
    }

    public void addArchivedCourse(Course course) {
        getArchivedCourses().add(course);
    }

    public void removeArchivedCourse(Course course) {
        getArchivedCourses().remove(course);
    }

    public void finishCourse(Course course){
        removeSubscribedCourse(course);
        addArchivedCourse(course);
    }


}
