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
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "producer_id", updatable = false)
    private Long producerId;

    @Column(name = "producer_name", nullable = false)
    private String producerName;

    @Column(name = "producer_address", nullable = false)
    private String producerAddress;

    @Column(name = "producer_create_date", nullable = false)
    private Date producerCreateDate;

    @Column(name = "producer_update_date", nullable = false)
    private Date producerUpdateDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_producer_id", referencedColumnName = "producer_id", nullable = false)
    private List<Product> products;
}
