package com.devon.building.converter;

import com.devon.building.builder.BuildingResquestBuilder;
import com.devon.building.model.request.BuildingSearchRequest;
import org.springframework.stereotype.Component;

@Component
public class BuildingRequestBuilderConverter {
    public BuildingResquestBuilder toBuildingSearchBuilder(BuildingSearchRequest params){
        BuildingResquestBuilder buildingResquestBuilder = new BuildingResquestBuilder.Builder()
                .setName(params.getName())
                .setNumberOfBasement(params.getNumberofbasement())
                .setWard(params.getWard())
                .setStreet(params.getStreet())
                .setDistrict(params.getDistrict())
                .setManagername(params.getManagername())
                .setManagerphone(params.getManagerphone())
                .setFloorarea(params.getFloorarea())
                .setArearentmin(params.getArearentmin())
                .setArearentmax(params.getArearentmax())
                .setRentpricemin(params.getRentpricemin())
                .setRentpricemax(params.getRentpricemax())
                .setStaffid(params.getStaffid())
                .setType(params.getType())
                .build();
        return buildingResquestBuilder;
    }

}
