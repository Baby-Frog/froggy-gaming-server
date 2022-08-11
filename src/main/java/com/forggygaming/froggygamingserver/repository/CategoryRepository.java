package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryByName(String categoryName);
}
