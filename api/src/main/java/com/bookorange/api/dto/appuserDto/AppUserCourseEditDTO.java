package com.bookorange.api.dto.appuserDto;

import com.bookorange.api.domain.Course;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppUserCourseEditDTO {
    @ApiModelProperty(value = "Id do usuário")
    private Long id;

    @ApiModelProperty(value = "Id do curso")
    private Course course;
}
