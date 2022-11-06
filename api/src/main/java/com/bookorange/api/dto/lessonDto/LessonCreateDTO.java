package com.bookorange.api.dto.lessonDto;

import com.bookorange.api.enumerator.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonCreateDTO {
    private String title;

    private String description;

    private String author;

    private String link;

    private String topic;

    private ContentType contentType;

    private Integer durationInMinutes;
}
