package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.dao.CustomerDao;
import com.forggygaming.froggygamingserver.entity.Customer;
import com.forggygaming.froggygamingserver.repository.CustomerJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServices {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CustomerJPA customerJPA;
    public Customer findByCusEmail(String email){
        return customerJPA.findCustomerByCusEmail(email);
    }
    public List<Customer> getAllCustomer(){
        return customerDao.getAllCustomer();
    }
    public Customer insertCustomer(Customer customer){
       return customerDao.insertCus(customer);

    }
    public Customer checkloginEmail(Customer customer){

      return customerDao.checkloginEmail(customer);
    }

}
