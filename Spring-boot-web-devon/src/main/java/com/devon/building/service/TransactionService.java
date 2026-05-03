package com.devon.building.service;

import com.devon.building.entity.Transaction;
import com.devon.building.model.dto.ResponseDTO;
import com.devon.building.model.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    TransactionDTO addTransaction(TransactionDTO transactionDTO);
    TransactionDTO updateTransaction(TransactionDTO transactionDTO);
    ResponseDTO deleteTransaction(Long id);
}
