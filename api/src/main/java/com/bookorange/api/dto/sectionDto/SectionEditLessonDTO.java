package com.bookorange.api.dto.sectionDto;

import com.bookorange.api.domain.Lesson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionEditLessonDTO {
    private Long sectionId;
    private Lesson lesson;
}
