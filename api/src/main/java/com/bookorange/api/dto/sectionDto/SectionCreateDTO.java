package com.bookorange.api.dto.sectionDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SectionCreateDTO {

    @NotBlank(message = "field not found")
    private String name;
}
