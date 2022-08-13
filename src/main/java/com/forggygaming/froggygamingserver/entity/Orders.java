package com.forggygaming.froggygamingserver.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    @NotNull
    private Date createdAt;

    @NotNull
    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order_id", referencedColumnName = "id", nullable = false)
    private List<OrderDetail> orderDetails;
}
