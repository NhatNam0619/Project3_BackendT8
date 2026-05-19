package com.devon.building.api.admin;

import com.devon.building.converter.CustomerDTOsConverter;
import com.devon.building.model.dto.CustomerResponseDTO;
import com.devon.building.model.dto.ResponseDTO;
import com.devon.building.model.dto.TransactionDTO;
import com.devon.building.service.CustomerService;
import com.devon.building.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class TransactionAPI {

    private final TransactionService transactionService;
    private final CustomerDTOsConverter customerDTOsConverter;
    private final CustomerService customerService;

    @PostMapping("/transaction")
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO)
    {
        transactionService.addTransaction(transactionDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(transactionDTO);
        responseDTO.setMessage("Done!!");
        return  ResponseEntity.ok().body(transactionDTO);
    }

    @PreAuthorize("hasRole('MANAGER') or @customerSecurity.isOwner(#p0.customerId)")
    @PutMapping("/transaction")
    public ResponseEntity<TransactionDTO> updateTransaction(@RequestBody TransactionDTO transactionDTO)
    {
        transactionService.updateTransaction(transactionDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(transactionDTO);
        responseDTO.setMessage("Done!!");
        return  ResponseEntity.ok().body(transactionDTO);
    }

    @PreAuthorize("hasRole('MANAGER') or @customerSecurity.isOwner(#id)")
    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<ResponseDTO> deleteTransaction(@PathVariable Long id)
    {
        ResponseDTO responseDTO = transactionService.deleteTransaction(id);
        return  ResponseEntity.ok().body(responseDTO);
    }


    @GetMapping("/{Id}/transaction")
    public ResponseEntity<ResponseDTO> loadTransactions(@PathVariable Long Id)
    {
        CustomerResponseDTO customerResponseDTO = customerDTOsConverter.convertCustomerResponseDTO(customerService.getCustomer(Id));
        Map<String, List<TransactionDTO>> transactionType = customerResponseDTO.getTransactionList();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(transactionType);
        responseDTO.setMessage("Done!!");
        return  ResponseEntity.ok().body(responseDTO);
    }
}
