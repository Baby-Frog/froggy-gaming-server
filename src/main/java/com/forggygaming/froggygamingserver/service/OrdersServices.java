package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.entity.Orders;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersServices {
    private final OrdersRepository ordersRepository;

    public List<Orders> getOrders() {
        return ordersRepository.findAll();
    }

    public ResponseEntity<ResponseObject> saveNewOrder(Orders orders) {
        return ResponseEntity.ok().body(new ResponseObject("OK", "save orders successfully !", ordersRepository.save(orders)));
    }

    public ResponseEntity<ResponseObject> deleteOrderById(Long id) {
        Optional<Orders> exists = ordersRepository.findById(id);
        if(exists.isPresent()) {
            ordersRepository.deleteById(id);
            return ResponseEntity.ok().body(new ResponseObject("OK", "Delete successfully !", id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "This order is not exists !", "id"));
    }

//    public ResponseEntity<ResponseObject> updateOrderById(Long id, Orders order) {
//        Orders orderUpdate = ordersRepository.findById(id).orElseThrow(() -> new IllegalStateException("This Order is not exists !"));
//        orderUpdate.s
//    }
}
