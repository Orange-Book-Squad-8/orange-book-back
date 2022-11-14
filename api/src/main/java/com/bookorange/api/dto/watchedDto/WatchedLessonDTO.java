package com.bookorange.api.dto.watchedDto;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Lesson;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WatchedLessonDTO {
    @ApiModelProperty(value = "Id do usuário")
    private AppUser user;

    @ApiModelProperty(value = "Id da lição")
    private Lesson lesson;
}
