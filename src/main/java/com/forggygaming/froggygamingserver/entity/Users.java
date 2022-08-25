package com.forggygaming.froggygamingserver.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String fullName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = LAZY)
    private Collection<Role> roles = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL, mappedBy = "users")
    private Customer customer;
    public Users(String fullName, String username, String password, Collection<Role> roles, Customer customer) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
