package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.dao.CustomerDao;
import com.forggygaming.froggygamingserver.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServices {
    @Autowired
    private CustomerDao customerDao;
    public List<Customer> getAllCustomer(){
        return customerDao.getAllCustomer();
    }
    public void insertCustomer(Customer customer){
        customerDao.insertCus(customer);
    }
}
