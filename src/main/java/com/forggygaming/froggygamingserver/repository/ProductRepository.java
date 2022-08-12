package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByName(String name);
}
