package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.Customer;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.service.CustomerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin("http://localhost:3000")

@RequiredArgsConstructor
public class CustomerController {
    private final CustomerServices customerServices;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Customer> getAllCustomer() {
        return customerServices.getAllCustomer();
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<ResponseObject> insertCustomer(@RequestBody Customer customer) throws Exception {

        Customer foundEmail = customerServices.findByCusEmail(customer.getCusEmail());
        Customer foundPhone = customerServices.findByCusPhoneNumber(customer.getCusPhoneNumber());

        return ((foundEmail == null)
                ? (foundPhone == null)
                ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "insert success", customerServices.insertCustomer(customer))
        ) : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("Failed", " Phone already exist", "")
        ) : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("Failed", "Email already exist", "")
        ));

    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<ResponseObject> updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
        Customer found = customerServices.findByCusId(id);
        return (found == null) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed", "Not found customer", "")
        ) : ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Update success", customerServices.updateCustomer(customer))
        );
    }

//
    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<ResponseObject> viewProfile() {

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        "OK", "Query success", customerServices.ViewProfile()
                )
        );

    }

}
