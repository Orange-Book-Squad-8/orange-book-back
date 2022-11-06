package com.bookorange.api.dto.lessonDto;

import com.bookorange.api.domain.Lesson;
import com.bookorange.api.enumerator.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonDTO {
    private Long id;

    private String title;

    private String description;

    private String author;

    private String link;

    private String topic;

    private ContentType contentType;

    private Integer durationInMinutes;

    public LessonDTO(Lesson lesson) {
        id = lesson.getId();
        title = lesson.getTitle();
        description = lesson.getDescription();
        author = lesson.getAuthor();
        link = lesson.getLink();
        topic = lesson.getTopic();
        contentType = lesson.getContentType();
        durationInMinutes = lesson.getDurationInMinutes();
    }
}
