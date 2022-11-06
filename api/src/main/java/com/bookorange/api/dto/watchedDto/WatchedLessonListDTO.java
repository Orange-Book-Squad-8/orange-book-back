package com.bookorange.api.dto.watchedDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WatchedLessonListDTO {
    private List<Long> watchedLesson;
    private Long lastWatched;
}
