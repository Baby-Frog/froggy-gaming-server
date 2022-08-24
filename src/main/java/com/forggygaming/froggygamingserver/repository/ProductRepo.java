package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findProductByProName(String proName);

    Product findProductByProId(Long proId);

    @Query("SELECT pro FROM Product pro WHERE pro.proName LIKE %?1%")
    List<Product> findProductsByProName(String proName);

    @Query("SELECT pro FROM Product pro ORDER BY pro.proName ASC")
    List<Product> ascSortProductsByProName();

    @Query("SELECT pro FROM Product pro ORDER BY pro.proName DESC")
    List<Product> descSortProductsByProName();

    @Query("SELECT pro FROM Product pro ORDER BY pro.proPrice ASC")
    List<Product> ascSortProductsByProPrice();
}
