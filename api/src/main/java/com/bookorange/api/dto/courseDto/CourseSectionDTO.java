package com.bookorange.api.dto.courseDto;

import com.bookorange.api.domain.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseSectionDTO {
    private Long id;
    private LinkedHashSet<Section> sections;
}
