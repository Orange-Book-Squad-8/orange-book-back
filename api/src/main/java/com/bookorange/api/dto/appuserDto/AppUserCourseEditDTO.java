package com.bookorange.api.dto.appuserDto;

import com.bookorange.api.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppUserCourseEditDTO {
    private Long id;
    private Course course;
}
