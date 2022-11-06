package com.bookorange.api.repository;

import com.bookorange.api.domain.WatchedList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WatchedListRepository extends JpaRepository<WatchedList, Long> {

    @Query(value = "SELECT * FROM TB_WATCHED WHERE WATCHED = ?1", nativeQuery = true)
    List<WatchedList> findWatchedListByUser(Boolean watched);
}
