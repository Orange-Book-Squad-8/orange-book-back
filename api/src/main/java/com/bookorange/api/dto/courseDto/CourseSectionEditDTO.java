package com.bookorange.api.dto.courseDto;

import com.bookorange.api.domain.Section;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseSectionEditDTO {
    @ApiModelProperty(value = "Id do curso")
    private Long id;

    @ApiModelProperty(value = "Nome da seção")
    private Section section;
}
