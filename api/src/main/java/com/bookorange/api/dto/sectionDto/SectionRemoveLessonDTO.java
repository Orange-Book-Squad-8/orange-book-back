package com.bookorange.api.dto.sectionDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionRemoveLessonDTO {
    private Long sessionId;
    private Long lessonId;
}
