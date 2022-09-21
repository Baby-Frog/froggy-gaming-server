package com.forggygaming.froggygamingserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@Transactional
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    private String cusFirstname;

    private String cusLastname;

    @Column( unique = true)
    private String cusEmail;

    @Column( unique = true)
    private Long cusPhoneNumber;
    @Column(unique = true)
    private String username;
    private String password;

    private String cusAvatarPath;
    private String cusAddress;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @ManyToMany(fetch = EAGER)
//    @JoinTable(name = "customer_roles",joinColumns = @JoinColumn(name = "customer_cus_id"),inverseJoinColumns = @JoinColumn(name = "roles_id"))
//    @JoinColumn(name = "pk_customer_roles",referencedColumnName = "id")
    private Collection<Role> roles = new ArrayList<>();

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Orders> orders;

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Customer(String cusFirstname, String cusLastname, String cusEmail, Long cusPhoneNumber, String username, String password, String cusAvatarPath, String cusAddress, LocalDate createdAt, LocalDate updatedAt, List<Orders> orders) {
        this.cusFirstname = cusFirstname;
        this.cusLastname = cusLastname;
        this.cusEmail = cusEmail;
        this.cusPhoneNumber = cusPhoneNumber;
        this.username = username;
        this.password = password;
        this.cusAvatarPath = cusAvatarPath;
        this.cusAddress = cusAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.orders = orders;
    }

    public void addOrder(Orders order) {
        order.setCustomer(this);
        orders.add(order);
    }
}
