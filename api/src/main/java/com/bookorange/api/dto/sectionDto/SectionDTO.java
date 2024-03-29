package com.bookorange.api.dto.sectionDto;

import com.bookorange.api.domain.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionDTO {
    private Long id;
    @NotBlank(message = "field not found")
    private String name;

    public SectionDTO(Section section) {
        id = section.getId();
        name = section.getName();
    }
}
