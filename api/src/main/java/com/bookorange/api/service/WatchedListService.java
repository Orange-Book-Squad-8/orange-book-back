package com.bookorange.api.service;

import com.bookorange.api.dto.watchedDto.WatchedLessonDTO;

public interface WatchedListService {
    void setWatched(WatchedLessonDTO watchedLesson);

    WatchedLessonDTO getWatchedLesson(Long userId);

    void setUnwatched(WatchedLessonDTO watchedLesson);
}
