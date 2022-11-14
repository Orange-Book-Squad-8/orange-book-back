package com.bookorange.api.dto.sectionDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionAddLessonDTO {
    @ApiModelProperty(value = "Id da seção")
    private Long sectionId;
    @ApiModelProperty(value = "Id da lição")
    private Long lessonId;
}
