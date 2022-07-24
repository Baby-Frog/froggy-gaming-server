package com.forggygaming.froggygamingserver.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @Column(name = "cusId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "cusName")
    private String cusName;
    @Column(name = "cusBirth")
    private Date cusBirth;
    @Column(name = "cusEmail")
    private String cusEmail;
    @Column(name = "cusPhone")
    private long cusPhone;
    @Column(name = "cusAddress")
    private String cusAddress;
    @Column(name = "cusAvt")
    private String cusAvt;

}
