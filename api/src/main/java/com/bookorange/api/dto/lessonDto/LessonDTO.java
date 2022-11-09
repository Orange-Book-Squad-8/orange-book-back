package com.bookorange.api.dto.lessonDto;

import com.bookorange.api.domain.Lesson;
import com.bookorange.api.enumerator.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonDTO {
    private Long id;

    @NotBlank(message = "field not found")
    private String title;

    @NotBlank(message = "field not found")
    private String description;

    @NotBlank(message = "field not found")
    private String author;

    @NotBlank(message = "field not found")
    @Pattern(regexp = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)")
    private String link;

    @NotBlank(message = "field not found")
    private String topic;

    @NotNull
    private ContentType contentType;

    @Positive
    @Min(1)
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
