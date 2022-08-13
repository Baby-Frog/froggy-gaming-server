package com.forggygaming.froggygamingserver.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    @NotNull @Column(unique = true)
    private String name;

    @NotNull
    private double price;

    private String description;

    @NotNull
    private Date createdAt;

    @NotNull
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Brand> brands;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_product_id", referencedColumnName = "id", nullable = false)
    private List<Image> images;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_product_id", referencedColumnName = "id", nullable = false)
    private List<ProductDetail> productDetails;
}
