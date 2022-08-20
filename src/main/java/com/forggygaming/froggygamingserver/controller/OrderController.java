package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.Orders;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.form.AddOrderToCustomerForm;
import com.forggygaming.froggygamingserver.service.OrderServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {
    private final OrderServices orderServices;

    @GetMapping
    public List<Orders> getOrders() {
        return orderServices.getOrders();
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseObject> saveNewOrder(@RequestBody Orders orders) {
        return orderServices.saveNewOrder(orders);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateOrderById(@PathVariable Long id, @RequestBody Orders orders) {
        return orderServices.updateOrderById(id, orders);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteOrderById(@PathVariable Long id) {
        return orderServices.deleteOrderById(id);
    }

    @PostMapping("/addtocustomer")
    public ResponseEntity<ResponseObject> addOrderToCustomer(@RequestBody AddOrderToCustomerForm form) {
        return orderServices.addOrderToCustomer(form);
    }
}

