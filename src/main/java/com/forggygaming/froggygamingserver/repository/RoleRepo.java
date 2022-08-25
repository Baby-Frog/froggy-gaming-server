package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByRoleName(String name);

}
