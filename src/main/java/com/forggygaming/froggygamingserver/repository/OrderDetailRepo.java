package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {
    OrderDetail findOrderDetailById(Long id);
}
