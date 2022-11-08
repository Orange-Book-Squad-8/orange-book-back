package com.bookorange.api.repository;

import com.bookorange.api.domain.UserWatchedLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserWatchedLessonRepository extends JpaRepository<UserWatchedLesson, Long> {

    @Query("select u from UserWatchedLesson u where u.appUser = ?1 and u.lesson = ?2")
    UserWatchedLesson findByAppUserAAndLesson(Long appUser, Long lesson);

    @Query("select u from UserWatchedLesson u where u.appUser = ?1 and u.watched = true")
    List<UserWatchedLesson> findUserWatchedLessonByUser(Long appUser);

}
