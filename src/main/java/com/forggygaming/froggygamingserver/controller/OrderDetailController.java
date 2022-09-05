package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.OrderDetail;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.form.AddOrderDetailToOrderForm;
import com.forggygaming.froggygamingserver.service.OrderDetailServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order-detail")
public class OrderDetailController {
    private final OrderDetailServices orderDetailServices;

    @GetMapping
    public List<OrderDetail> getOrderDetails() {
        return orderDetailServices.getOrderDetails();
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")

    public ResponseEntity<ResponseObject> saveNewOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailServices.saveNewOrderDetail(orderDetail);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")

    public ResponseEntity<ResponseObject> deleteOrderDetailById(@PathVariable Long id) {
        return orderDetailServices.deleteOrderDetailById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")

    public ResponseEntity<ResponseObject> updateOrderDetailById(@PathVariable Long id, @RequestBody OrderDetail orderDetail) {
        return orderDetailServices.updateOrderDetailById(id, orderDetail);
    }

    @PostMapping("/add-to-order")
    public ResponseEntity<ResponseObject> addOrderDetailToOrder(@RequestBody AddOrderDetailToOrderForm form) {
        return orderDetailServices.addOrderDetailToOrder(form);
    }

}
