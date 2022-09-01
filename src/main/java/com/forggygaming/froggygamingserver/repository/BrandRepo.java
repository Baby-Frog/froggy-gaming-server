package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {
    Brand findBrandByBrandName(String brandName);

    Brand findBrandById(Long id);
}
