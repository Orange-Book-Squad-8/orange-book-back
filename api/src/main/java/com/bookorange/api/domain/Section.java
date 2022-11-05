package com.bookorange.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany
    @JsonIgnore
    private LinkedList<Lesson> lessons;

    public void addLesson(Lesson lesson) {
        if (lessons == null) throw new IllegalArgumentException("lesson cannot be null");
        getLessons().add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        if (lessons == null) throw new IllegalArgumentException("lesson cannot be null");
        getLessons().remove(lesson);
    }

    public Integer getDuration() {
        return lessons.stream().mapToInt(Lesson::getDurationInMinutes).sum();
    }

}
