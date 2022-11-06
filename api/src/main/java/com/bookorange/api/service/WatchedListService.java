package com.bookorange.api.service;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.repository.AppUserRepository;
import com.bookorange.api.repository.WatchedListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchedListService {

    private final WatchedListRepository watchedListRepository;

    @Autowired
    public WatchedListService(WatchedListRepository watchedListRepository){
        this.watchedListRepository = watchedListRepository;
    }
}
