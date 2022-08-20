package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.entity.OrderDetail;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.repository.OrderDetailRepo;
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "This order detail is not exists !", ""));
        }
        orderDetailUpdate.setUpdatedAt(LocalDate.now());
        orderDetailUpdate.setQuantity(orderDetail.getQuantity());
        orderDetailUpdate.setAddress(orderDetailUpdate.getAddress());
        orderDetailUpdate.setPrice(orderDetail.getPrice());
        orderDetailUpdate.setOrder(orderDetail.getOrder());
        orderDetailUpdate.setProduct(orderDetail.getProduct());
        orderDetailRepo.save(orderDetailUpdate);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Update successfully", orderDetailUpdate));
    }
}
