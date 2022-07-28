package com.forggygaming.froggygamingserver.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cusId;

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

    public Customer() {
    }

    public Customer(long cusId,
                    String cusName,
                    String cusPassword,
                    String cusEmail,
                    long cusPhone,
                    Date cusBirth,
                    String cusAddress,
                    String cusAvt) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.cusPassword = cusPassword;
        this.cusEmail = cusEmail;
        this.cusPhone = cusPhone;
        this.cusBirth = cusBirth;
        this.cusAddress = cusAddress;
        this.cusAvt = cusAvt;
    }

    public Customer(String cusName,
                    String cusPassword,
                    String cusEmail,
                    long cusPhone,
                    Date cusBirth,
                    String cusAddress,
                    String cusAvt) {
        this.cusName = cusName;
        this.cusPassword = cusPassword;
        this.cusEmail = cusEmail;
        this.cusPhone = cusPhone;
        this.cusBirth = cusBirth;
        this.cusAddress = cusAddress;
        this.cusAvt = cusAvt;
    }
}
