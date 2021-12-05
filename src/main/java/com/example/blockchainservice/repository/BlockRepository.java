package com.example.blockchainservice.repository;

import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Blockchain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block,String> {
}
