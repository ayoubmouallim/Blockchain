package com.example.blockchainservice.services;


import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Blockchain;
import com.example.blockchainservice.entities.Transaction;

import java.util.Date;
import java.util.List;

public interface BlockChainService {


    Blockchain createBlockChain();
    void minerBlock(String min_adr, List<Transaction> pendingTransactions);

}
