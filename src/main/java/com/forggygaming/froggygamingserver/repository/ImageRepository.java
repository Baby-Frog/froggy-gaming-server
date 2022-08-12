package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
