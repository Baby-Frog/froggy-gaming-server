package com.forggygaming.froggygamingserver.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Column(name = "proId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long proId;
    @Column(name = "proName")
    private String proName;
    @Column(name = "proDesc")
    private String proDesc;
    @Column(name = "proQty")
    private int proQty;
    @Column(name = "proPrice")
    private Long proPrice;
    @Column(name = "cateId")
    private String cateId;
    @Column(name = "proImg")
    private String proImg;

}
