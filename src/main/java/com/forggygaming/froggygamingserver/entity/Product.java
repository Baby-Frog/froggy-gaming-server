package com.forggygaming.froggygamingserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity @Data @Transactional
@NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long proId;
    private String proName;
    private Long proPrice;
    @Lob
    @Column(length = 999999999)
    private String proDesc;
    private Boolean proStatus;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private List<Image> images;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private ProductDetail productDetail;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public void addImage(Image image) {
        image.setProduct(this);
        images.add(image);
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetail.setProduct(this);
        orderDetails.add(orderDetail);
    }
    public void addProductDetail(ProductDetail productDetail) {
        productDetail.setProduct(this);
    }

    // xoa item
    public void removeImage() {
        for(Image image : images) {
            image.setProduct(null);
        }
        images.clear();
    }

    public void removeProductDetail(ProductDetail productDetail) {
        productDetail.setProduct(null);
    }
}
