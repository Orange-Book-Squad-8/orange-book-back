package com.bookorange.api.service;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.dto.watchedDto.WatchedLessonDTO;

import java.util.List;

public interface AppUserWatchedLessonService {
    void setWatched(WatchedLessonDTO watchedLesson);

    List<Long> getWatchedLessonList(AppUser user);

    void setUnwatched(WatchedLessonDTO watchedLesson);
}
