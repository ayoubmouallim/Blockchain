package com.example.blockchainservice.repository;

import com.example.blockchainservice.entities.Blockchain;
import com.example.blockchainservice.entities.Miner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MinerRepository extends JpaRepository<Miner,String> {
   // Miner findBy
}
