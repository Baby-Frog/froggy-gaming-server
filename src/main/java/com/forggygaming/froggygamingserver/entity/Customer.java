package com.forggygaming.froggygamingserver.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor @AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull @Column(unique = true)
    private String email;

    @NotNull @Column(unique = true)
    private Long phoneNumber;

    @NotNull
    private String password;

    @NotNull
    private Date createdAt;

    @NotNull
    private Date updatedAt;

    private String avatar;
}
