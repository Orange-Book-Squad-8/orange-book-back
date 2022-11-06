package com.bookorange.api.dto.courseDto;

import com.bookorange.api.domain.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseSectionDTO {
    private Long id;
    private List<Section> sections;
}
