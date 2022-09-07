package com.forggygaming.froggygamingserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;
    private String model;
    private String connect;
    private String age;
    private String weight;
    private String compatible;
    private String size;
    private String cable;
    private String color;
    private String led;
    private String accessories;
    private String layout;
    private String specialFeature;
    private String keyboardSwitch;
    private String keyboardKeycap;
    private String mouseDpi;
    private String mouseSensor;
    private String chairLifter;
    private String chairPillow;
    private String chairWheel;
    private String chairMaximum;
    private String microphoneFrequency;
    private String microphoneSens;
    private String microphoneBitrate;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
