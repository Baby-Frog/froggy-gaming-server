package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
}
