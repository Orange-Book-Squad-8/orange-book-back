package com.bookorange.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany
    private List<Lesson> lessons = new ArrayList<>();

    public void addLesson(Lesson lesson) {
        if (lesson == null) throw new IllegalArgumentException("lesson cannot be null");
        if (lessons.contains(lesson)) throw new IllegalArgumentException("lesson already exists");
        getLessons().add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        if (lesson == null) throw new IllegalArgumentException("lesson cannot be null");
        if (!lessons.contains(lesson)) throw new IllegalArgumentException("lesson does not exist");
        getLessons().remove(lesson);
    }

    public Integer getDuration() {
        return lessons.stream().mapToInt(Lesson::getDurationInMinutes).sum();
    }

}
