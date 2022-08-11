package com.forggygaming.froggygamingserver.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    @NotNull
    private Long quantity;

    @NotNull
    private Long total;

    @NotNull
    private Date createdAt;

    @NotNull
    private Date UpdatedAt;
}
