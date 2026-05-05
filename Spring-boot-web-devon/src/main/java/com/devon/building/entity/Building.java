package com.devon.building.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "building")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Building implements Serializable {

    @Serial
    private static final long serialVersionUID = -1000119078147252957L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "building",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<RentArea> rentAreas = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid"),
            inverseJoinColumns = @JoinColumn(name = "staffid")
    )
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "building",orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "street", length = 255, nullable = false)
    private String street;

    @Column(name = "ward", length = 255, nullable = false)
    private String ward;

    @Column(name = "district", length = 255, nullable = false)
    private String district;

    String structure;

    @Column(name = "numberofbasement", nullable = false)
    Long numberofbasement;

    @Column(name = "floorarea", nullable = false)
    Long floorarea;

    String direction;
    String level;

    @Column(name = "rentprice", nullable = false)
    double rentprice;

    String rentpricedescription;
    String servicefee;
    String carfee;
    String motorbikefee;
    String overtimefee;
    String waterfee;
    String electricityfee;
    String deposit;
    String payment;
    String renttime;
    String decorationtime;
    String brokeragefee;

    @Column(name = "type", nullable = false)
    String type;

    String note;
    String linkofbuilding;
    String map;

    @Column(name = "managername", nullable = false)
    String managername;

    @Column(name = "managerphone", nullable = false)
    String managerphone;


    @Lob
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createddate", nullable = false)
    private Date createDate;


}
