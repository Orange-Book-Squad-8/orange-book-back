package com.bookorange.api.dto.courseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseCreateSectionDTO {
    private Long courseId;
    private String sectionName;
}
