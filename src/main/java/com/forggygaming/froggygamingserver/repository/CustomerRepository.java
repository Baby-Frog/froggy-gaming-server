package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByEmail(String email);

    Customer findByPassword(String password);
}
