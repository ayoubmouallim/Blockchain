package com.example.blockchainservice.repository;

import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {
}
