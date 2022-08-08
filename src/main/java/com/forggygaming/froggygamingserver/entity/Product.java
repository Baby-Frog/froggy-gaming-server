package com.forggygaming.froggygamingserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", updatable = false)
    private Long productId;

    @Column(name = "product_name", nullable = false, unique = true)
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_price", nullable = false)
    private double productPrice;

    @Column(name = "product_create_date", nullable = false)
    private Date productCreateDate;

    @Column(name = "product_update_date", nullable = false)
    private Date productUpdateDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_product_id", referencedColumnName = "product_id", nullable = false)
    private List<Image> images;
}
