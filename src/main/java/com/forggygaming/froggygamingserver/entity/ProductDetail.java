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
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    private String model;

    private String connect;

    private Long age;

    private double weight;

    private String compatible;

    private String size;

    private String color;

    private String accessories;

    private String layout;

    private String specialFeature;

    private String keyboardSwitch;

    private String keyboardKeyCap;

    private String mouseDpi;

    private String mouseSensor;

    private String chairLifter;

    private String chairPillow;

    private String chairWheel;

    private String chairMaximum;

    private String microphoneFrequency;

    private String microphoneBitrate;

    private String microphoneSens;

    private String microphoneSpl;

    private Date createdAt;

    private Date updatedAt;
}
