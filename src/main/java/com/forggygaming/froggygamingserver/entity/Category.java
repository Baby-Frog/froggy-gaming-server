package com.forggygaming.froggygamingserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Entity @Data @Transactional
@NoArgsConstructor @AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long cateId;

    @Column(nullable = false)
    private String cateName;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "category")
    private List<Product> products;

    public void addProduct(Product product) {
        product.setCategory(this);
        products.add(product);
    }
}
