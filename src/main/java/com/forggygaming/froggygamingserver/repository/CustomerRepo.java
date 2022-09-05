package com.forggygaming.froggygamingserver.repository;

import com.forggygaming.froggygamingserver.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
    Customer findByCusEmail(String email);
    Customer findByCusPhoneNumber(Long phoneNumber);
    Customer findByUsername(String username);

}
