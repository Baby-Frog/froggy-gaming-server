package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJPA extends JpaRepository<Customer,Long> {
}
