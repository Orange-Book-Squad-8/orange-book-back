package com.bookorange.api.dto.appuserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddCourseToUserDTO {
    private Long userId;
    private Long courseId;
}
