package com.bookorange.api.service;

import com.bookorange.api.domain.WatchedList;
import com.bookorange.api.dto.watchedDto.WatchedLessonDTO;

import java.util.List;

public interface WatchedListService {
    void setWatched(WatchedLessonDTO watchedLesson);

    List<WatchedList> getWatchedLesson(Long userId);

    WatchedList getLastWatchedLesson(Long userId);

    void setUnwatched(WatchedLessonDTO watchedLesson);
}
