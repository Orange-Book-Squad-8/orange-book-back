package com.bookorange.api.dto.lessonDto;

import com.bookorange.api.enumerator.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonContentDTO {
    private Long id;
    private ContentType contentType;
}
