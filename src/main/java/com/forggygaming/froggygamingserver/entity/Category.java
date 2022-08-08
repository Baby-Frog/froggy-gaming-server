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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id", updatable = false)
    private Long categoryId;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_create_date", nullable = false)
    private Date categoryCreateDate;

    @Column(name = "category_update_date", nullable = false)
    private Date categoryUpdateDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_category_id", referencedColumnName = "category_id", nullable = false)
    private List<Product> products;
}
