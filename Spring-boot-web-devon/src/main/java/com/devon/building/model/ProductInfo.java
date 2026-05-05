package com.devon.building.model;

import com.devon.building.entity.Building;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInfo {
    private Long id;
    private String name;
    private double rentPrice;
    private String street;
    private String ward;
    private String district;

    public ProductInfo() {
    }

    public ProductInfo(Building building) {
        this.id = building.getId();
        this.name = building.getName();
        this.rentPrice = building.getRentprice();
        this.street = building.getStreet();
        this.ward = building.getWard();
        this.district = building.getDistrict();
    }

    // Using in JPA/Hibernate query
    public ProductInfo(Long id, String name, double rentPrice, String street, String ward, String district) {
        this.id = id;
        this.name = name;
        this.rentPrice = rentPrice;
        this.street = street;
        this.ward = ward;
        this.district = district;
    }

}
