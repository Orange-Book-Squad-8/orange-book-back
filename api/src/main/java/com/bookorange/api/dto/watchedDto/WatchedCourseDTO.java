package com.bookorange.api.dto.watchedDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WatchedCourseDTO {
    private Long userId;
    private Long courseId;
}
