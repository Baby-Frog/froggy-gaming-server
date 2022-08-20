package com.forggygaming.froggygamingserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Transactional
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long cusId;

    @Column(nullable = false)
    private String cusFirstname;

    @Column(nullable = false)
    private String cusLastname;

    @Column(nullable = false, unique = true)
    private String cusEmail;

    @Column(nullable = false, unique = true)
    private Long cusPhoneNumber;

    @Column(nullable = false)
    private String cusPassword;
    private String cusAvatarPath;
    private String cusAddress;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Orders> orders;

    public void addOrder(Orders orders) {
        orders.setCustomer(this);
        this.orders.add(orders);
    }
}
