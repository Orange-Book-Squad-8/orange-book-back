package com.bookorange.api.dto.appuserDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddCourseToUserDTO {

    @ApiModelProperty(value = "Id do usuário")
    private Long userId;
    @ApiModelProperty(value = "Id do curso")
    private Long courseId;
}
