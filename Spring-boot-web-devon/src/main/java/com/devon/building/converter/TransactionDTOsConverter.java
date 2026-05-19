package com.devon.building.converter;

import com.devon.building.entity.Transaction;
import com.devon.building.model.dto.TransactionDTO;
import com.devon.building.repository.UserRepository;
import com.devon.building.utils.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class TransactionDTOsConverter {

    private final ModelMapper modelMapper;

    public TransactionDTO toTransactionDTO(Transaction transaction){
        TransactionDTO transactionDTO = modelMapper.map(transaction,TransactionDTO.class);
        return transactionDTO;
    }
    public List<TransactionDTO> toListTransactionDTO(List<Transaction> transactions){
        boolean isManager = SecurityUtil.hasRole("MANAGER");
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        for(Transaction transaction :transactions)
        {
            if(transaction.isActive() || isManager)
            {TransactionDTO transactionDTO = modelMapper.map(transaction,TransactionDTO.class);
            transactionDTOList.add(transactionDTO);}
        }
        transactionDTOList.sort(
                Comparator.comparing(TransactionDTO::getCreatedDate, Comparator.nullsLast(Comparator.reverseOrder())));
        return transactionDTOList;
    }
}
