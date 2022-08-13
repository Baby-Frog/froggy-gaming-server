package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
}
