package com.bookorange.api.dto.courseDto;

import com.bookorange.api.domain.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseSectionEditDTO {
    private Long id;
    private Section section;
}
