package com.devon.building.service.impl;

import com.devon.building.builder.CustomerResquestBuilder;
import com.devon.building.converter.CustomerDTOsConverter;
import com.devon.building.converter.CustomerRequestBuilderConverter;
import com.devon.building.converter.StaffResponseDTOsConverter;
import com.devon.building.entity.Customer;
import com.devon.building.entity.User;
import com.devon.building.model.dto.*;
import com.devon.building.model.request.CustomerSearchRequest;
import com.devon.building.repository.CustomerRepository;
import com.devon.building.repository.UserRepository;
import com.devon.building.service.CustomerService;
import com.devon.building.utils.SecurityUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDTOsConverter customerDTOsConverter;
    private final CustomerRequestBuilderConverter customerRequestBuilderConverter;
    private final UserRepository userRepository;
    private final StaffResponseDTOsConverter staffResponseDTOsConverter;


    @Override
    public List<CustomerResponseDTO> findAll(CustomerSearchRequest params) {

        if (!SecurityUtil.hasRole("MANAGER")) {
            params.setStaffid(userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).getId());}
        CustomerResquestBuilder customerResquestBuilder = customerRequestBuilderConverter.toCustomerSearchBuilder(params);
        List<Customer> customers = customerRepository.findAll(customerResquestBuilder);
        List<CustomerResponseDTO> customerResponseDTOS = new ArrayList<>();
        for(Customer customer : customers)
        {
            CustomerResponseDTO customerResponseDTO = customerDTOsConverter.convertCustomerResponseDTO(customer);
            customerResponseDTOS.add(customerResponseDTO);
        }
        return customerResponseDTOS;
    }

    @Override
    public CustomerResponseDTO updateAssignmentCustomer(StaffIdsDTO staffIdsDTO) {
        Customer customer = customerRepository.findById(staffIdsDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Building not found within id: " + staffIdsDTO.getId()));
        List<User> users = userRepository.findByIdIn(staffIdsDTO.getStaffids());
        customer.setUsers(users);
        return customerDTOsConverter.convertCustomerResponseDTO(customerRepository.findById(staffIdsDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Building not found within id: " + staffIdsDTO.getId())));
    }

    @Override
    public List<StaffResponseDTO> listStaffs(Long customerId) {
        List<User> allStaff = userRepository.findByUserRoleAndActive("ROLE_STAFF",true);
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer not found within id: " + customerId));
        List<User> assignmentUser = customer.getUsers();
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        for(User staff: allStaff)
        {
            StaffResponseDTO staffResponseDTO = staffResponseDTOsConverter.convertStaffResponseDTO(staff);
            if(assignmentUser.contains(staff))
            {
                staffResponseDTO.setCheckbox("checked");
            }
            else {
                staffResponseDTO.setCheckbox("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        return staffResponseDTOS;
    }

    @Override
    public CustomerResponseDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = customerDTOsConverter.toCustomerEntity(customerDTO);
        customer.setIsActive(true);
        customerRepository.save(customer);
        return customerDTOsConverter.convertCustomerResponseDTO(customer);
    }

    @Override
    public Customer getCustomer(long id) {
            return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Building not found within id: " + id));
    }

    @Override
    public List<CustomerResponseDTO> loadCustomers(List<Long> ids) {
        List<Customer> customers = customerRepository.findAllById(ids);
        List<CustomerResponseDTO> customerResponseDTOS = new ArrayList<>();
        for(Customer c: customers)
        {
            CustomerResponseDTO customerResponseDTO = customerDTOsConverter.convertCustomerResponseDTO(c);
            customerResponseDTOS.add(customerResponseDTO);
        }
        return customerResponseDTOS;
    }

    @Override
    public ResponseDTO deleteCustomer(List<Long> ids) {
        ResponseDTO responseDTO = new ResponseDTO();
        for (Long id : ids) {
            Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Building not found within id: " + id));
            if (SecurityUtil.hasRole("MANAGER")&&!customer.getUsers().isEmpty()) {
                responseDTO.setMessage("Khách hàng đang được giao cho nv !!");
                return responseDTO;
            }
            if (customer == null) {
                responseDTO.setMessage("Hãy chọn khách hàng cần xóa !!");
                return responseDTO;
            }
            else {
                if (SecurityUtil.hasRole("MANAGER")) {
                customerRepository.deleteById(id);}
                //else customer.setIsActive(false);
            }
        }
        responseDTO.setMessage("Xoa Thanh Cong" );
        return responseDTO;
    }

    @Override
    public Customer findCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found within id: " + id));
    }

    @Override
    public CustomerResponseDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(customerDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Customer not found within id: " + customerDTO.getId()));
        customer.setFullName(customerDTO.getFullName());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());
        customer.setDemand(customerDTO.getDemand());
        customer.setCompanyname(customerDTO.getCompanyname());
        customer.setStatus(customerDTO.getStatus());
        customerRepository.save(customer);
        return customerDTOsConverter.convertCustomerResponseDTO(customer);
    }
}
