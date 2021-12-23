package com.example.blockchainservice.repository;

import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Blockchain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<Block,String> {
}
