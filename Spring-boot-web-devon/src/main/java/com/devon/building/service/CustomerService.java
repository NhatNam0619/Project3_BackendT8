package com.devon.building.service;

import com.devon.building.entity.Building;
import com.devon.building.entity.Customer;
import com.devon.building.model.dto.*;
import com.devon.building.model.request.BuildingSearchRequest;
import com.devon.building.model.request.CustomerSearchRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    List<CustomerResponseDTO> findAll(CustomerSearchRequest params);
    CustomerResponseDTO updateAssignmentCustomer(StaffIdsDTO staffIdsDTO);
    List<StaffResponseDTO> listStaffs(Long customerId) ;
    CustomerResponseDTO saveCustomer(CustomerDTO customerDTO);
    Customer getCustomer(long id);
    List<CustomerResponseDTO> loadCustomers(List<Long> Id);
    ResponseDTO deleteCustomer(List<Long> ids);
    Customer findCustomer(Long id);
    CustomerResponseDTO updateCustomer (CustomerDTO customerDTO);


}
