package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.Customer;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.repository.CustomerRepository;
import com.forggygaming.froggygamingserver.service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    private final CustomerServices customerServices;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerServices customerServices, CustomerRepository customerRepository) {
        this.customerServices = customerServices;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getAllCustomer() {

        return customerServices.getAllCustomer();
    }

    @PostMapping
    public ResponseEntity<ResponseObject> insertCustomer(@RequestBody Customer customer) throws Exception {

        List<Customer> found = customerServices.findByEmailOrPhone(customer);

        return (found.isEmpty()) ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "insert success", customerServices.insertCustomer(customer))
        ) : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject(
                        "Failed", "Email or Phone already exist", ""
                )
        );

    }

    @GetMapping("/login")
    public ResponseEntity<ResponseObject> checkLogin(@RequestBody Customer customer) {
        Customer found = customerServices.checkLogin(customer);

        return (found == null) ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Failed", "Username or password invalid", "")
        ) : ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "success", found)
        );
    }


}
