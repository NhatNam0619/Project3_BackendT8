package com.devon.building.converter;

import com.devon.building.entity.Building;
import com.devon.building.entity.Customer;
import com.devon.building.entity.RentArea;
import com.devon.building.entity.User;
import com.devon.building.enums.District;
import com.devon.building.enums.StatusCode;
import com.devon.building.model.dto.BuildingDTO;
import com.devon.building.model.dto.BuildingResponseDTO;
import com.devon.building.model.dto.CustomerDTO;
import com.devon.building.model.dto.CustomerResponseDTO;
import com.devon.building.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class CustomerDTOsConverter {
    private final ModelMapper modelMapper;

    public CustomerResponseDTO convertCustomerResponseDTO(Customer customer) {
        CustomerResponseDTO customerResponseDTO = modelMapper.map(customer, CustomerResponseDTO.class);
        customerResponseDTO.setStatusname(StatusCode.getName(customer.getStatus()));
        return customerResponseDTO;
    }
    public Customer toCustomerEntity(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        return customer;}

    public CustomerDTO toCustomerDTO(Customer customer)
    {
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }

}
