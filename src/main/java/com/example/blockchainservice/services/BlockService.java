package com.example.blockchainservice.services;


import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Transaction;

import java.util.Date;
import java.util.List;

public interface BlockService {

     Block createBlock(List<Transaction> transactions, String prevHash);
     String calculateHash(Block block);
     Block mineBlock(int difficulty,Block block);

    List<Block> getAllBlocks();
}
