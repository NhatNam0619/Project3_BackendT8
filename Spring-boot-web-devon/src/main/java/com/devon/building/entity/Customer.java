package com.devon.building.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {


    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "assignmentcustomer",
            joinColumns = @JoinColumn(name = "customerid"),
            inverseJoinColumns = @JoinColumn(name = "staffid")
    )
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    @Column(name = "fullname", length = 250, nullable = false)
    private String fullName;

    @Column(name = "phone", length = 10, nullable = false)
    private Long phone;

    @Column(name = "email", length = 250)
    private String email;

    @Column(name = "companyname", length = 250)
    private String companyname;

    @Column(name = "demand", length = 250)
    private String demand;

    @Column(name = "status", length = 250, nullable = false)
    private String status;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
