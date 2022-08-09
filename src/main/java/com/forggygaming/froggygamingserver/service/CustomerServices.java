package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.dao.CustomerDao;
import com.forggygaming.froggygamingserver.entity.Customer;
import com.forggygaming.froggygamingserver.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServices {
    private final CustomerDao customerDao;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServices(CustomerDao customerDao, CustomerRepository customerRepository) {
        this.customerDao = customerDao;
        this.customerRepository = customerRepository;
    }

    public Customer findByCusEmail(String email) {
        return customerRepository.findByCusEmail(email);
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

    public Customer checkLogin(Customer customer) {
        return customerDao.checkLogin(customer);
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
