package com.example.blockchainservice.services;


import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Blockchain;
import com.example.blockchainservice.entities.Transaction;
import com.example.blockchainservice.exceptions.BlockChaineNotFoundException;

import java.util.Date;
import java.util.List;

public interface BlockChainService {


    Blockchain createBlockChain(String name,int difficulty,int reward);
    void mineBlock(String blockchainID,String min_adr, List<Transaction> pendingTransactions ) ;
    Block getLastBlock(String blockchainID);
    Boolean isValid(String blockchainID);
    double getAddressSolde(String blockchainID,String addr);
    Blockchain getBlockchaineByID(String id) throws BlockChaineNotFoundException;

}
