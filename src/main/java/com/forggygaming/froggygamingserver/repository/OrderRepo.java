package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Long> {
    Orders findOrderById(Long id);
}
