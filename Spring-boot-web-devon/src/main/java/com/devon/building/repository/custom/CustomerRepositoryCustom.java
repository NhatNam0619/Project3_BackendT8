package com.devon.building.repository.custom;

import com.devon.building.builder.CustomerResquestBuilder;
import com.devon.building.entity.Customer;
import com.devon.building.model.request.CustomerSearchRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepositoryCustom {
    List<Customer> findAll(CustomerResquestBuilder customerResquestBuilder);
}
