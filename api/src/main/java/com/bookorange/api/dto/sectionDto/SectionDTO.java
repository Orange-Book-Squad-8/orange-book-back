package com.bookorange.api.dto.sectionDto;

import com.bookorange.api.domain.Lesson;
import com.bookorange.api.domain.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionDTO {
    private Long id;
    private String name;
    private List<Lesson> lessons;

    public SectionDTO(Section section) {
        id = section.getId();
        name = section.getName();
        lessons = section.getLessons();
    }
}
