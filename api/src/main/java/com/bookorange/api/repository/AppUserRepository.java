package com.bookorange.api.repository;

import com.bookorange.api.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    AppUser findByUsername(String username);

    List<AppUser> listAll();
}
