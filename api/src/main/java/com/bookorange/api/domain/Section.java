package com.bookorange.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;

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

    @OneToMany
    private LinkedList<Lesson> lessons;

    public void addLesson(Lesson lesson) {
        if (lessons == null) throw new IllegalArgumentException("lesson cannot be null");
        if (lessons.contains(lesson)) throw new IllegalArgumentException("lesson already exists");
        getLessons().add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        if (lessons == null) throw new IllegalArgumentException("lesson cannot be null");
        if (!lessons.contains(lesson)) throw new IllegalArgumentException("lesson does not exists");
        getLessons().remove(lesson);
    }

    public Integer getDuration() {
        return lessons.stream().mapToInt(Lesson::getDurationInMinutes).sum();
    }

}
