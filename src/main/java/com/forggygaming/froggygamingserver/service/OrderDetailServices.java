package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.entity.OrderDetail;
import com.forggygaming.froggygamingserver.entity.Orders;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.form.AddOrderDetailToOrderForm;
import com.forggygaming.froggygamingserver.repository.OrderDetailRepo;
import com.forggygaming.froggygamingserver.repository.OrderRepo;
import com.forggygaming.froggygamingserver.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServices {
    private final OrderDetailRepo orderDetailRepo;

    private final OrderRepo orderRepo;

    private final ProductRepo productRepo;

    public List<OrderDetail> getOrderDetails() {
        return orderDetailRepo.findAll();
    }

    public ResponseEntity<ResponseObject> saveNewOrderDetail(OrderDetail orderDetail) {
        orderDetail.setCreatedAt(LocalDate.now());
        orderDetail.setUpdatedAt(LocalDate.now());
        orderDetailRepo.save(orderDetail);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Save new order detail successfully", orderDetail));
    }

    public ResponseEntity<ResponseObject> deleteOrderDetailById(Long id) {
        OrderDetail orderDetail = orderDetailRepo.findOrderDetailById(id);
        if (orderDetail == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "This order detail is not exists !", id));
        }
        return ResponseEntity.ok().body(new ResponseObject("OK", "Delete this order detail successfully", id));
    }

    public ResponseEntity<ResponseObject> updateOrderDetailById(Long id, OrderDetail orderDetail) {
        OrderDetail orderDetailUpdate = orderDetailRepo.findOrderDetailById(id);
        if (orderDetailUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "This order detail is not exists !", id));
        }
        orderDetailUpdate.setUpdatedAt(LocalDate.now());
        orderDetailUpdate.setQuantity(orderDetail.getQuantity());
        orderDetailUpdate.setTotalPrice(orderDetail.getTotalPrice());
        orderDetailUpdate.setOrder(orderDetail.getOrder());
        orderDetailRepo.save(orderDetailUpdate);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Update successfully", orderDetailUpdate));
    }

    public ResponseEntity<ResponseObject> addOrderDetailToOrder(AddOrderDetailToOrderForm form) {
        Orders order = orderRepo.findOrderById(form.getOrderId());
        OrderDetail orderDetail = orderDetailRepo.findOrderDetailById(form.getOrderDetailId());
        if(order == null || orderDetail == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "Not found", ""));
        }
        order.setUpdatedAt(LocalDate.now());
        order.addOrderDetail(orderDetail);
        orderRepo.save(order);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Update successfully", order));
    }
}
