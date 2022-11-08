package com.bookorange.api.service;

import com.bookorange.api.dto.watchedDto.WatchedLessonDTO;

import java.util.List;

public interface AppUserWatchedLessonService {
    void setWatched(WatchedLessonDTO watchedLesson);

    List<Long> getWatchedLessonList(Long userId);

    void setUnwatched(WatchedLessonDTO watchedLesson);
}
