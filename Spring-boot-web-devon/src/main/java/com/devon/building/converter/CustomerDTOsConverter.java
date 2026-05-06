package com.devon.building.converter;

import com.devon.building.entity.*;
import com.devon.building.enums.District;
import com.devon.building.enums.StatusCode;
import com.devon.building.model.dto.*;
import com.devon.building.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class CustomerDTOsConverter {
    private final ModelMapper modelMapper;
    private final TransactionDTOsConverter transactionDTOsConverter;

    public CustomerResponseDTO convertCustomerResponseDTO(Customer customer) {
        CustomerResponseDTO customerResponseDTO = modelMapper.map(customer, CustomerResponseDTO.class);
        customerResponseDTO.setStatusname(StatusCode.getName(customer.getStatus()));
        Map<String, List<TransactionDTO>> transactionList = new HashMap<>();
        if (customer.getTransactions() != null) {
            transactionList = transactionDTOsConverter.toListTransactionDTO(customer.getTransactions())
                    .stream()
                    .collect(Collectors.groupingBy(TransactionDTO::getCode));
        }
        customerResponseDTO.setTransactionList(transactionList);
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
