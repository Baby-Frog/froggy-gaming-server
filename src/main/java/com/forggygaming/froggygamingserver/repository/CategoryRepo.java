package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Override
    Page<Category> findAll(Pageable pageable);

    Category findCategoryByCateName(String cateName);

    Category findCategoryByCateId(Long cateId);
}
