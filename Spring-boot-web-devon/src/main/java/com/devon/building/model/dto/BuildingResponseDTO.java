package com.devon.building.model.dto;
import com.devon.building.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.aspectj.apache.bcel.generic.LocalVariableGen;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingResponseDTO extends AbstractDTO {
    Long id;
    String name;
    String address;
    Long numberofbasement;
    String managername;
    String managerphone;
    Long floorarea;
    String emptyarea;
    String rentarea;
    Long rentprice;
    String brokeragefee;
    String servicefee;
}
