package com.bookorange.api.dto.courseDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseCreateSectionDTO {

    @ApiModelProperty(value = "Id do curso")
    private Long courseId;

    @ApiModelProperty(value = "Id da seção")
    private String sectionName;
}
