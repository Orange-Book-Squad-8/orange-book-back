package com.bookorange.api.dto.courseDto;

import com.bookorange.api.dto.sectionDto.CompleteSectionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompleteCourseDTO {
    private CourseDTO courseDto;
    private List<CompleteSectionDTO> completeSectionDTO = new ArrayList<>();

    public void addSection(CompleteSectionDTO dto) {
        completeSectionDTO.add(dto);
    }
}
