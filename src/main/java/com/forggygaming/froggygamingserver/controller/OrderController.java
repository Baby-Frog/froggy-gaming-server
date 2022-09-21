package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.Orders;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.form.AddOrderToCustomerForm;
import com.forggygaming.froggygamingserver.service.OrderServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
@CrossOrigin("http://localhost:3000")

public class OrderController {
    private final OrderServices orderServices;

    @GetMapping
    public List<Orders> getOrders() {
        return orderServices.getOrders();
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<ResponseObject> saveNewOrder(@RequestBody Orders orders) {
        return orderServices.saveNewOrder(orders);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")

    public ResponseEntity<ResponseObject> updateOrderById(@PathVariable Long id, @RequestBody Orders orders) {
        return orderServices.updateOrderById(id, orders);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")

    public ResponseEntity<ResponseObject> deleteOrderById(@PathVariable Long id) {
        return orderServices.deleteOrderById(id);
    }

    @PostMapping("/add-to-customer")
    public ResponseEntity<ResponseObject> addOrderToCustomer(@RequestBody AddOrderToCustomerForm form) {
        return orderServices.addOrderToCustomer(form);
    }
}

