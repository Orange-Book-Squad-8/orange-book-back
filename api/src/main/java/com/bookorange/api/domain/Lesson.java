package com.bookorange.api.domain;

import com.bookorange.api.enumerator.ContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private String author;

    private String link;


    private String topic;


    private ContentType contentType;


    private Integer durationInMinutes;

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", link='" + link + '\'' +
                ", topic='" + topic + '\'' +
                ", contentType=" + contentType +
                ", durationInMinutes=" + durationInMinutes +
                '}';
    }
}
