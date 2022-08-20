package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
    Image findImageByImgName(String imgName);
}
