package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.dao.CustomerDao;
import com.forggygaming.froggygamingserver.entity.Customer;
import com.forggygaming.froggygamingserver.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServices {
    private final CustomerDao customerDao;
    private final CustomerRepo customerRepo;

    public Customer findByCusEmail(String email) {
        return customerRepo.findByCusEmail(email);
    }

    public List<Customer> getAllCustomer() {
        return customerDao.getAllCustomer();
    }

    public Customer updateCustomer(Customer customer){
        return customerDao.updateCustomer(customer);
    }
    public Customer insertCustomer(Customer customer) {
        return customerDao.insertCus(customer);
    }


    public Customer findByEmail(String email) {
        return customerDao.findByEmail(email);
    }
    public Customer findByPhone(Long phone) {
        return customerDao.findByPhone(phone);
    }
    public Customer getById(long id){
        return customerDao.getById(id);
    }

}
