package com.bookorange.api.service.implementation;

import com.bookorange.api.domain.Lesson;
import com.bookorange.api.domain.WatchedList;
import com.bookorange.api.dto.watchedDto.WatchedLessonDTO;
import com.bookorange.api.service.WatchedListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchedListServiceImp implements WatchedListService {

    //private final WatchedListRepository watchedListRepository;

    /*
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    public WatchedListServiceImp(WatchedListRepository watchedListRepository) {
        this.watchedListRepository = watchedListRepository;
    }
     */

    @Override
    public void setWatched(WatchedLessonDTO watchedLesson) {
        List<Lesson> watchedLessons = getWatchedLesson(watchedLesson.getUserId()).stream().map(WatchedList::getLesson).toList();
        if (watchedLessons.stream().map(Lesson::getId).toList().contains(watchedLesson.getLessonId())) {
            throw new RuntimeException("Lesson already watched");
        }/*
        AppUser user = appUserRepository.findById(watchedLesson.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Lesson lesson = lessonRepository.findById(watchedLesson.getLessonId()).orElseThrow(() -> new RuntimeException("Lesson not found"));

        WatchedList watchedList = new WatchedList();
        watchedList.setWatched(true);
        watchedList.setLastWatched(true);
        watchedList.setAppUser(user);
        watchedList.setLesson(lesson);

        watchedListRepository.save(watchedList);

        WatchedList lastWatched = getLastWatchedLesson(watchedLesson.getUserId());
        lastWatched.setLastWatched(false);
        watchedListRepository.save(lastWatched);*/
    }

    @Override
    public List<WatchedList> getWatchedLesson(Long userId) {
        return null; //watchedListRepository.findWatchedListByUser(userId);
    }

    @Override
    public WatchedList getLastWatchedLesson(Long userId) {
        return null; //watchedListRepository.findLastWatchedUser(userId);
    }

    @Override
    public void setUnwatched(WatchedLessonDTO watchedLesson) {
        List<Lesson> watchedLessons = getWatchedLesson(watchedLesson.getUserId()).stream().map(WatchedList::getLesson).toList();
        if (watchedLessons.stream().map(Lesson::getId).toList().contains(watchedLesson.getLessonId())) {
            throw new RuntimeException("Lesson never watched");
        }/*
        AppUser user = appUserRepository.findById(watchedLesson.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Lesson lesson = lessonRepository.findById(watchedLesson.getLessonId()).orElseThrow(() -> new RuntimeException("Lesson not found"));

        WatchedList watchedList = new WatchedList();
        watchedList.setWatched(false);
        watchedList.setAppUser(user);
        watchedList.setLesson(lesson);

        watchedListRepository.save(watchedList);*/
    }
}
