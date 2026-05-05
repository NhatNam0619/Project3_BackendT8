package com.devon.building.model.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingDTO {

        private Long id;
        @NotBlank(message = "RentArea not be blank")
        private String rentarea;
        private String name;
        @NotBlank(message = "street not be blank")
        private String street;
        @NotBlank(message = "ward not be blank")
        private String ward;
        @NotBlank(message = "district not be blank")
        private String district;

        @NotNull(message = "numberofbasement not be null")
        @Min(value = 1, message = "numberofbasement must >= 0")
        Long numberofbasement;

        @NotNull(message = "floorarea not be null")
        @Min(value = 1, message = "floorarea must >= 0")
        Long floorarea;

        @Min(value = 1, message = "rentprice must >= 0")
        Long rentprice;

        String direction;
        String level;
        String structure;
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

        @Size(min = 1,message = "type must more then 1")
        List<String> type;
        String note;
        String linkofbuilding;
        String map;
        @NotBlank(message = "managername not be blank")
        String managername;
        @Size(min = 10, message = "managerphone must be 10 number")
        String managerphone;
}
