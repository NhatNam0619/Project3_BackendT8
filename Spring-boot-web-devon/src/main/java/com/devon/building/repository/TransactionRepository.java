package com.devon.building.repository;

import com.devon.building.entity.RentArea;
import com.devon.building.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Transaction findTransactionById(Long id);
}
