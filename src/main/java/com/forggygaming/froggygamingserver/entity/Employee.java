package com.forggygaming.froggygamingserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id", updatable = false)
    private Long employeeId;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "employee_email", nullable = false, unique = true)
    private String employeeEmail;

    @Column(name = "employee_phone", nullable = false, unique = true)
    private Long employeePhone;

    @Column(name = "employee_password", nullable = false)
    private String employeePassword;

    @Column(name = "employee_create_date", nullable = false)
    private Date employeeCreateDate;

    @Column(name = "employee_update_date", nullable = false)
    private Date employeeUpdateDate;

    @OneToOne
    @JoinColumn(name = "fk_role_id")
    private Role role;
}
