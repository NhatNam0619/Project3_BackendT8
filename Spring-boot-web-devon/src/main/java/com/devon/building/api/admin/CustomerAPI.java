package com.devon.building.api.admin;

import com.devon.building.converter.CustomerDTOsConverter;
import com.devon.building.converter.TransactionDTOsConverter;
import com.devon.building.entity.Customer;
import com.devon.building.entity.Transaction;
import com.devon.building.model.dto.*;
import com.devon.building.service.CustomerService;
import com.devon.building.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerAPI {

    private final CustomerService customerService;

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/assignmentcustomer")
    public ResponseEntity<CustomerResponseDTO> updateAssignmentCustomer(@RequestBody StaffIdsDTO staffIdsDTO)
    {
        CustomerResponseDTO customerResponseDTO = customerService.updateAssignmentCustomer(staffIdsDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(customerResponseDTO);
        responseDTO.setMessage("Done!!");
        return  ResponseEntity.ok().body(customerResponseDTO);
    }


    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{customerId}/staffs")
    public ResponseEntity<ResponseDTO> loadStaffs(@PathVariable Long customerId)
    {
        List<StaffResponseDTO> staffResponseDTOS = customerService.listStaffs(customerId);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("Done!!");
        return  ResponseEntity.ok().body(responseDTO);
    }


    @PostMapping
    public ResponseEntity<ResponseDTO> createCustomer(@RequestBody @Valid CustomerDTO customerDTO, BindingResult bindingResult) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            if(bindingResult.hasErrors())
            {
                List<String> errorMessages = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                responseDTO.setMessage("Validation failed");
                responseDTO.setDetail(errorMessages);
                return ResponseEntity.badRequest().body(responseDTO);
            }
            CustomerResponseDTO customerEntity = customerService.saveCustomer(customerDTO);
            responseDTO.setData(customerEntity);
            responseDTO.setMessage("Done!!");
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or @customerSecurity.isOwner(#p0.id)")
    @PutMapping
    public ResponseEntity<ResponseDTO> updateCustomer(@RequestBody @Valid CustomerDTO customerDTO, BindingResult bindingResult) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            if(bindingResult.hasErrors())
            {
                List<String> errorMessages = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                responseDTO.setMessage("Validation failed");
                responseDTO.setDetail(errorMessages);
                return ResponseEntity.badRequest().body(responseDTO);
            }
            CustomerResponseDTO customerEntity = customerService.updateCustomer(customerDTO);
            responseDTO.setData(customerEntity);
            responseDTO.setMessage("Done!!");
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{Ids}/customers")
    public ResponseEntity<ResponseDTO> loadCustomers(@PathVariable List<Long> Ids)
    {
        List<CustomerResponseDTO> customerResponseDTOS = customerService.loadCustomers(Ids);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(customerResponseDTOS);
        responseDTO.setMessage("Done!!");
        return  ResponseEntity.ok().body(responseDTO);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("{ids}")
    public ResponseEntity<ResponseDTO> deleteCustomer(@PathVariable List<Long> ids) {
        ResponseDTO responseDTO = customerService.deleteCustomer(ids);
        return ResponseEntity.ok(responseDTO);
    }

}
