package com.example.blockchainservice.services;


import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Blockchain;
import com.example.blockchainservice.entities.Miner;
import com.example.blockchainservice.entities.Transaction;
import com.example.blockchainservice.repository.MinerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BlockChainServiceImpl implements BlockChainService{

    @Autowired
    private BlockService blockService;
    @Autowired
    private MinerRepository minerRepository;

    @Override
    public Blockchain createBlockChain() {
    /*    Blockchain blockchain = new Blockchain();
        Block block = new Block();

        blockchain.setNom("GenisisBlock");
        blockchain.getBlocks().add(blockService.createBlock());
        return blockchain;*/
        return null;
    }

    @Override
    public void minerBlock(String min_adr, List<Transaction> pendingTransactions) {

    }

   /* @Override
    public Blo minerBlock(String min_adr, List<Transaction> pendingTransactions,String pres_hash)
    {
        Blockchain blockchain = createBlockChain();
        Block block = blockService.createBlock();

        for (Transaction transaction:pendingTransactions)
        {
            blockService.minerBlock(blockchain.getDifficulté(),block);
        }
        Miner miner =  minerRepository.findBy()
    }*/

}
