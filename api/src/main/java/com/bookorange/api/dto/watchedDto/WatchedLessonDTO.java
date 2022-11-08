package com.bookorange.api.dto.watchedDto;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Lesson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WatchedLessonDTO {
    private AppUser user;
    private Lesson lesson;
}
