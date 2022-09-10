package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {
    Brand findBrandByBrandName(String brandName);

    Brand findBrandById(Long id);

    @Query("SELECT brand FROM Brand brand WHERE brand.brandName LIKE %?1%")
    List<Brand> searchBrandsByBrandName(String brandName);
}
