package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
    Customer findByCusEmail(String email);

    Customer findByCusPassword(String password);
}
