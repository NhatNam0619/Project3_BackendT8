package com.devon.building.repository;

import com.devon.building.entity.Customer;
import com.devon.building.model.request.CustomerSearchRequest;
import com.devon.building.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long>, CustomerRepositoryCustom {
    List<Customer> findByIsActive(Boolean active);
    Customer findCustomerById(Long id);
}
