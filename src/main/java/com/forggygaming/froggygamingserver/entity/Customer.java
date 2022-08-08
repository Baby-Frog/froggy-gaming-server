package com.forggygaming.froggygamingserver.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", updatable = false)
    private Long customerId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_password", nullable = false)
    private String cusPassword;

    @Column(name = "customer_email", unique = true, nullable = false)
    private String cusEmail;

    @Column(name = "customer_phone", unique = true, nullable = false)
    private Long cusPhone;

    @Column(name = "customer_create_date", nullable = false)
    private Date customerCreateDate;

    @Column(name = "customer_update_date", nullable = false)
    private Date customerUpdateDate;

    @Column(name = "customer_address")
    private String customerAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_customer_id", referencedColumnName = "customer_id", nullable = false)
    private List<OrderDetail> orderDetails;
}
