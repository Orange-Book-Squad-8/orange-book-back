package com.bookorange.api.repository;

import com.bookorange.api.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
