package com.bookorange.api.dto.sectionDto;

import com.bookorange.api.domain.Lesson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompleteSectionDTO {
    private Long id;
    private String name;
    private List<Lesson> lessons = new ArrayList<>();
}
