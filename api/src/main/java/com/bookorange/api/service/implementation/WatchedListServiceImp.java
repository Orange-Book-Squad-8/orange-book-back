package com.bookorange.api.service.implementation;

import com.bookorange.api.dto.watchedDto.WatchedLessonDTO;
import com.bookorange.api.dto.watchedDto.WatchedLessonListDTO;
import com.bookorange.api.repository.WatchedListRepository;
import com.bookorange.api.service.WatchedListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchedListServiceImp implements WatchedListService {

    private final WatchedListRepository watchedListRepository;

    @Autowired
    public WatchedListServiceImp(WatchedListRepository watchedListRepository) {
        this.watchedListRepository = watchedListRepository;
    }


    @Override
    public void setWatched(WatchedLessonDTO watchedLesson) {

    }

    @Override
    public WatchedLessonListDTO getWatchedLesson(Long userId) {
        return null;
    }

    @Override
    public void setUnwatched(WatchedLessonDTO watchedLesson) {

    }
}
