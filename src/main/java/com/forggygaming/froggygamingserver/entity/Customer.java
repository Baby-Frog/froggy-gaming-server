package com.forggygaming.froggygamingserver.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @Column(name = "cus_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "cus_name")
    private String cusName;
    @Column(name = "cus_birth")
    private Date cusBirth;
    @Column(name = "cus_email",unique = true)
    private String cusEmail;
    @Column(name = "cus_phone",unique = true)
    private long cusPhone;
    @Column(name = "cus_password")
    private String cusPassword;
    @Column(name = "cus_address")
    private String cusAddress;
    @Column(name = "cus_avt")
    private String cusAvt;



}
