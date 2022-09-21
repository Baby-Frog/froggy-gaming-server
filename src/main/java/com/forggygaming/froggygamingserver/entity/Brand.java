package com.forggygaming.froggygamingserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Entity @Data @Transactional
@NoArgsConstructor @AllArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String brandName;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "brand")
    private List<Product> products;

    public void addProduct(Product product) {
        product.setBrand(this);
        products.add(product);
    }

    public void removeProduct(Product product) {
        product.setBrand(null);
        products.remove(product);
    }
}
