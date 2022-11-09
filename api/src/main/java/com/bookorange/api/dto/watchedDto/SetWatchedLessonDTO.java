package com.bookorange.api.dto.watchedDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SetWatchedLessonDTO {
    private Long userId;
    private Long lessonId;
}
