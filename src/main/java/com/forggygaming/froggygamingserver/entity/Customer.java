package com.forggygaming.froggygamingserver.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
public class Customer {
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String cusName;

    @Column(nullable = true)
    private String cusPassword;

    @Column(unique = true, nullable = true)
    private String cusEmail;

    @Column(unique = true, nullable = true)
    private long cusPhone;

    @Column(nullable = false)
    private Date cusBirth;

    @Column(nullable = false)
    private String cusAddress;

    @Column(nullable = false)
    private String cusAvt;
}
