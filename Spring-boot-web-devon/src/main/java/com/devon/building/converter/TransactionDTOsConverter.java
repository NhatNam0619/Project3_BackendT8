package com.devon.building.converter;

import com.devon.building.entity.Transaction;
import com.devon.building.model.dto.TransactionDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class TransactionDTOsConverter {
    private final ModelMapper modelMapper;

    public TransactionDTO toTransactionDTO(Transaction transaction){
        TransactionDTO transactionDTO = modelMapper.map(transaction,TransactionDTO.class);
        return transactionDTO;
    }
}
