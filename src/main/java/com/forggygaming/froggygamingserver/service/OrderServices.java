package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.entity.Customer;
import com.forggygaming.froggygamingserver.entity.Orders;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.form.AddOrderToCustomerForm;
import com.forggygaming.froggygamingserver.repository.CustomerRepo;
import com.forggygaming.froggygamingserver.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServices {
    private final OrderRepo orderRepo;

    private final CustomerRepo customerRepo;

    public List<Orders> getOrders() {
        return orderRepo.findAll();
    }

    public ResponseEntity<ResponseObject> saveNewOrder(Orders orders) {
        orders.setCreatedAt(LocalDate.now());
        orders.setUpdatedAt(LocalDate.now());
        orderRepo.save(orders);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Save new order successfully", orders));
    }

    public ResponseEntity<ResponseObject> updateOrderById(Long id, Orders orders) {
        Orders ordersUpdate = orderRepo.findOrderById(id);
        if (ordersUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "Not found", id));
        }
        ordersUpdate.setUpdatedAt(LocalDate.now());
        ordersUpdate.setCustomer(orders.getCustomer());
        orderRepo.save(ordersUpdate);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", ordersUpdate));
    }

    public ResponseEntity<ResponseObject> deleteOrderById(Long id) {
        Orders exists = orderRepo.findOrderById(id);
        if (exists == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "This order is not exists !", null));
        }
        orderRepo.deleteById(id);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Delete order successfully", id));
    }

    public ResponseEntity<ResponseObject> addOrderToCustomer(AddOrderToCustomerForm form) {
        Orders orders = orderRepo.findOrderById(form.getOrderId());
        Customer customer = customerRepo.findByCusEmail(form.getCusEmail());
        if (orders == null || customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FASLE", "Not found", ""));
        }
        customer.addOrder(orders);
        customer.setUpdatedAt(LocalDate.now());
        customerRepo.save(customer);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Delete order successfully", customer));
    }
}
