package com.bookorange.api.dto.watchedDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WatchedLessonDTO {
    private Long userId;
    private Long lessonId;
}
