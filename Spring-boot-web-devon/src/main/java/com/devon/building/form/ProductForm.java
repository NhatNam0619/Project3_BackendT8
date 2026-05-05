package com.devon.building.form;

import com.devon.building.entity.Building;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductForm {
    private Long id;
    private String name;
    private double rentPrice;
 
    private boolean newProduct = false;
 
    // Upload file.
    private MultipartFile fileData;
 
    public ProductForm() {
        this.newProduct= true;
    }
 
    public ProductForm(Building building) {
        this.id = building.getId();
        this.name = building.getName();
        this.rentPrice = building.getRentprice();
    }
 
}
