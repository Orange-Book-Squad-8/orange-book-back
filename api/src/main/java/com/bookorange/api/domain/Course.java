package com.bookorange.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String author;

    @OneToMany
    @JsonIgnore
    private ArrayList<Lesson> lessons;

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
        return lessons.stream().mapToDouble(lesson -> lesson.getWatched().compareTo(true)).sum() / lessons.size();
    }

    public List<Lesson> getWatchedLessons() {
        return lessons.stream().filter(Lesson::getWatched).collect(Collectors.toList());
    }

    public List<Lesson> getNotWatchedLessons() {
        return lessons.stream().filter(lesson -> !lesson.getWatched()).collect(Collectors.toList());
    }


}
