package com.devon.building.converter;

import com.devon.building.builder.BuildingResquestBuilder;
import com.devon.building.builder.CustomerResquestBuilder;
import com.devon.building.model.request.BuildingSearchRequest;
import com.devon.building.model.request.CustomerSearchRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerRequestBuilderConverter {
    public CustomerResquestBuilder toCustomerSearchBuilder(CustomerSearchRequest params){
        CustomerResquestBuilder customerResquestBuilder = new CustomerResquestBuilder.Builder()
                .setFullName(params.getFullName())
                .setPhone( params.getPhone() != null
                        ? Long.parseLong(params.getPhone())
                        : null)
                .setEmail(params.getEmail())
                .setStaffid(params.getStaffid())
                .setStatus(params.getStatus())
                .build();
        return customerResquestBuilder;
    }
}
