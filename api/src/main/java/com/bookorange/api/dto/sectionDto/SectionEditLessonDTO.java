package com.bookorange.api.dto.sectionDto;

import com.bookorange.api.domain.Lesson;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionEditLessonDTO {

    @ApiModelProperty(value = "Id da seção")
    private Long sectionId;

    @ApiModelProperty(value = "Id da lição")
    private Lesson lesson;
}
