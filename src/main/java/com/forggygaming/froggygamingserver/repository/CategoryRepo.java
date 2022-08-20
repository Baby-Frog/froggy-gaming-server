package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category findCategoryByCateName(String cateName);
}
