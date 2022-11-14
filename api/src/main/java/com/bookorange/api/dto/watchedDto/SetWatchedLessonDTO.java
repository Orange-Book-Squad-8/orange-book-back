package com.bookorange.api.dto.watchedDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SetWatchedLessonDTO {
    @ApiModelProperty(value = "Id do usuário")
    private Long userId;

    @ApiModelProperty(value = "Id da lição")
    private Long lessonId;
}
