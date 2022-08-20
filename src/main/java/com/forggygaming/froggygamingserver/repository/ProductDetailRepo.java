package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepo extends JpaRepository<ProductDetail, Long> {
    ProductDetail findProductDetailById(Long id);
}
