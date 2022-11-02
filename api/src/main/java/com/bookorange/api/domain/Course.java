package com.bookorange.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String author;

    @OneToMany
    @JsonIgnore
    private List<Lesson> lessons;

    public void addLesson(Lesson lesson) {
        getLessons().add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        getLessons().remove(lesson);
    }

    public Integer getDuration() {
        return lessons.stream().mapToInt(Lesson::getDurationInMinutes).sum();
    }

    public Double getProgress() {
        return lessons.stream().mapToDouble(lesson -> Boolean.compare(lesson.getWatched(),false)).sum() / lessons.size();
    }

    public List<Lesson> getWatchedLessons() {
        return lessons.stream().filter(Lesson::getWatched).collect(Collectors.toList());
    }

    public List<Lesson> getUnwatchedLessons() {
        return lessons.stream().filter(lesson -> !lesson.getWatched()).collect(Collectors.toList());
    }

}
