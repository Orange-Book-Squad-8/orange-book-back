package com.bookorange.api.repository;

import com.bookorange.api.domain.WatchedList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchedListRepository extends JpaRepository<WatchedList, Long> {

    @Query("select u from WatchedList u where u.appUser = ?1 and u.lesson = ?2")
    WatchedList findByAppUserAAndLesson(Long appUser, Long lesson);

    @Query("select u from WatchedList u where u.appUser = ?1 and u.watched = true")
    List<WatchedList> findWatchedListByUser(Long appUser);

    @Query("select WatchedList from WatchedList u where u.appUser = ?1 and u.lastWatched = true")
    WatchedList findLastWatchedUser(Long appUser);
    
}
