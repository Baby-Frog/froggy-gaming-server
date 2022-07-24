package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.dao.CustomerDao;
import com.forggygaming.froggygamingserver.entity.Customer;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerServices customerServices;
    @GetMapping
    public List<Customer> getAllCustomer(){

        return customerServices.getAllCustomer();
    }
    @PostMapping
    public void insertCustomer(@RequestBody Customer customer){
        customerServices.insertCustomer(customer);
    }
}
