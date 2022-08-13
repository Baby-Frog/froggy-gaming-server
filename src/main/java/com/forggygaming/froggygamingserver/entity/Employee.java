package com.forggygaming.froggygamingserver.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Employee {
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

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;
}
