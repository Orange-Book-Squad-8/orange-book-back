package com.bookorange.api.service;

import com.bookorange.api.dto.watchedDto.WatchedLessonDTO;
import com.bookorange.api.dto.watchedDto.WatchedLessonListDTO;

public interface WatchedListService {
    void setWatched(WatchedLessonDTO watchedLesson);

    WatchedLessonListDTO getWatchedLesson(Long userId);

    void setUnwatched(WatchedLessonDTO watchedLesson);
}
