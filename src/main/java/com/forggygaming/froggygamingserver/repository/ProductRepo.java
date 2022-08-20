package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findProductByProName(String proName);

    Product findProductByProId(Long proId);
}
