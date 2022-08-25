package com.forggygaming.froggygamingserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String cusFirstname;

    private String cusLastname;

    @Column( unique = true)
    private String cusEmail;

    @Column( unique = true)
    private Long cusPhoneNumber;


    private String cusAvatarPath;
    private String cusAddress;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Orders> orders;

    public void addOrder(Orders orders) {
        orders.setCustomer(this);
        this.orders.add(orders);
    }
}
