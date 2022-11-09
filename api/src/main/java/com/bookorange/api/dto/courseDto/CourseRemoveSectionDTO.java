package com.bookorange.api.dto.courseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseRemoveSectionDTO {
    private Long courseId;
    private Long sectionId;
}
