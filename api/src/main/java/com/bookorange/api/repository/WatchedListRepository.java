package com.bookorange.api.repository;

import com.bookorange.api.domain.WatchedList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchedListRepository extends JpaRepository<WatchedList, Long> {
}
