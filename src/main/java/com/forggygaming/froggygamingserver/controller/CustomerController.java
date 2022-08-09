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

        Customer foundEmail = customerServices.findByCusEmail(customer.getCusEmail());
        Customer foundPhone=customerServices.findByPhone(customer.getCusPhone());

        return ((foundEmail==null)
                    ?(foundPhone==null)
                        ? ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject("OK", "insert success", customerServices.insertCustomer(customer))
                        ):ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                            new ResponseObject("Failed", " Phone already exist", "")
                    ):ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("Failed", "Email already exist", "")
        ));

    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject>updateCustomer(@RequestBody Customer customer,@PathVariable long id){
        Customer found=customerServices.getById(id);
        return (found==null)?ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed","Not found customer","")
        ):ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Update success",customerServices.updateCustomer(customer))
        );
    }



    @GetMapping("/login")
    public ResponseEntity<ResponseObject> checkLogin(@RequestBody Customer customer) {
        Customer found = customerServices.checkLogin(customer);

        return (found == null) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed", "Username or password invalid", "")
        ) : ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "success", found)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> viewProfile(@PathVariable long id){

        return  ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        "OK", "Query success",customerServices.getById(id)
                )
        )  ;

    }

}
