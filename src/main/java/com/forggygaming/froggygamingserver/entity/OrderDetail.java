package com.forggygaming.froggygamingserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_detail_id", updatable = false)
    private Long orderDetailId;

    @Column(name = "order_detail_price", nullable = false)
    private double orderDetailPrice;

    @Column(name = "order_detail_create_date", nullable = false)
    private Date orderDetailCreateDate;

    @Column(name = "order_detail_update_date", nullable = false)
    private Date orderDetailUpdateDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;
}