package com.devon.building.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders", //
        uniqueConstraints = { @UniqueConstraint(columnNames = "Order_Num") })
public class Order implements Serializable {
 
    private static final long serialVersionUID = -2576670215015463100L;
 
    @Id
    @Column(name = "ID", length = 50)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany(mappedBy = "order",orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();
 
    @Column(name = "Order_Date", nullable = false)
    private Date orderDate;
 
    @Column(name = "Order_Num", nullable = false)
    private int orderNum;
 
    @Column(name = "Amount", nullable = false)
    private double amount;
 
    @Column(name = "Customer_Name", length = 255, nullable = false)
    private String customerName;
 
    @Column(name = "Customer_Address", length = 255, nullable = false)
    private String customerAddress;
 
    @Column(name = "Customer_Email", length = 128, nullable = false)
    private String customerEmail;
 
    @Column(name = "Customer_Phone", length = 128, nullable = false)
    private String customerPhone;
}
