package com.bookorange.api.service.implementation;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Lesson;
import com.bookorange.api.domain.UserWatchedLesson;
import com.bookorange.api.dto.watchedDto.WatchedLessonDTO;
import com.bookorange.api.handler.exception.ForbiddenException;
import com.bookorange.api.repository.UserWatchedLessonRepository;
import com.bookorange.api.service.AppUserWatchedLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppUserWatchedLessonServiceImp implements AppUserWatchedLessonService {
    private final UserWatchedLessonRepository userWatchedLessonRepository;


    @Autowired
    public AppUserWatchedLessonServiceImp(UserWatchedLessonRepository userWatchedLessonRepository) {
        this.userWatchedLessonRepository = userWatchedLessonRepository;
    }


    @Override
    public void setWatched(WatchedLessonDTO watchedLesson) {
        UserWatchedLesson appUserWatched = userWatchedLessonRepository.findByAppUserAAndLesson(
                watchedLesson.getUser(),
                watchedLesson.getLesson());
        if (appUserWatched == null) {
            UserWatchedLesson watched = new UserWatchedLesson();
            watched.setWatched(true);
            watched.setAppUser(watchedLesson.getUser());
            watched.setLesson(watchedLesson.getLesson());

            userWatchedLessonRepository.save(watched);
        } else {
            throw new ForbiddenException("Lesson already watched");
        }

    }

    @Override
    public List<Long> getWatchedLessonList(AppUser user) {
        List<Lesson> watchedLessons = userWatchedLessonRepository.findUserWatchedLessonByUser(user).stream().map(UserWatchedLesson::getLesson).toList();
        return watchedLessons.stream().map(Lesson::getId).toList();
    }

    @Override
    public void setUnwatched(WatchedLessonDTO watchedLesson) {
        UserWatchedLesson appUserWatched = userWatchedLessonRepository.findByAppUserAAndLesson(
                watchedLesson.getUser(),
                watchedLesson.getLesson());
        if (appUserWatched != null) {
            appUserWatched.setWatched(false);
            appUserWatched.setRemovedAt(LocalDate.now());

            userWatchedLessonRepository.save(appUserWatched);
        } else {
            throw new RuntimeException("Lesson never watched");
        }
    }
}
