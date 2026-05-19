package com.devon.building.service.impl;

import com.devon.building.converter.TransactionDTOsConverter;
import com.devon.building.entity.Customer;
import com.devon.building.entity.Transaction;
import com.devon.building.model.dto.ResponseDTO;
import com.devon.building.model.dto.TransactionDTO;
import com.devon.building.repository.CustomerRepository;
import com.devon.building.repository.TransactionRepository;
import com.devon.building.repository.UserRepository;
import com.devon.building.service.TransactionService;
import com.devon.building.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Override
    public TransactionDTO addTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long staffId = userRepository.findByUserName(username).getId();
        Customer customer = customerRepository.findCustomerById(transactionDTO.getCustomerId());
        transaction.setCustomer(customer);
        transaction.setCode(transactionDTO.getCode());
        transaction.setNote(transactionDTO.getNote());
        transaction.setStaffid(staffId);
        transactionRepository.save(transaction);
        return transactionDTO;
    }

    @Override
    public TransactionDTO updateTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.findTransactionById(transactionDTO.getId());
        transaction.setNote(transactionDTO.getNote());
        transactionRepository.save(transaction);
        return null;
    }

    @Override
    public ResponseDTO deleteTransaction(Long id) {
        if (SecurityUtil.hasRole("MANAGER")) {
            transactionRepository.deleteById(id);}
        else transactionRepository.findTransactionById(id).setActive(false);
        return null;
    }

}
