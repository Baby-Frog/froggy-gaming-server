package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.OrderDetail;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.service.OrderDetailServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orderdetail")
public class OrderDetailController {
    private final OrderDetailServices orderDetailServices;

    @GetMapping
    public List<OrderDetail> getOrderDetails() {
        return orderDetailServices.getOrderDetails();
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseObject> saveNewOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailServices.saveNewOrderDetail(orderDetail);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteOrderDetailById(@PathVariable Long id) {
        return orderDetailServices.deleteOrderDetailById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateOrderDetailById(@PathVariable Long id, @RequestBody OrderDetail orderDetail) {
        return orderDetailServices.updateOrderDetailById(id, orderDetail);
    }


}
