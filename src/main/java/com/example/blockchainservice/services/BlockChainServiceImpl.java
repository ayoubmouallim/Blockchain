package com.example.blockchainservice.services;


import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Blockchain;
import com.example.blockchainservice.entities.Miner;
import com.example.blockchainservice.entities.Transaction;
import com.example.blockchainservice.exceptions.BlockChaineNotFoundException;
import com.example.blockchainservice.repository.BlockChainRepository;
import com.example.blockchainservice.repository.MinerRepository;
import com.example.blockchainservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BlockChainServiceImpl implements BlockChainService{

    @Autowired
    private BlockService blockService;
    @Autowired
    private BlockChainRepository blockChainRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Blockchain createBlockChain(String name,int difficulty,int reward) {
       Blockchain blockchain = new Blockchain();
       // first block on the blockchain
        Block GenisisBlock = blockService.createBlock(null,"");
        blockService.mineBlock(difficulty,GenisisBlock);
        blockchain.setNom(name);
        blockchain.setId(UUID.randomUUID().toString());
        blockchain.getBlocks().add(GenisisBlock);
        blockchain.setDifficulté(difficulty);
        blockchain.setMiningReward(reward);

        blockChainRepository.save(blockchain);

        return blockchain;
    }

   @Override
    public void mineBlock(String blockchainID,String min_adr, List<Transaction> pendingTransactions) {

       Blockchain blockchain = blockChainRepository.findById(blockchainID).get();
       Block lastBlock = getLastBlock(blockchainID);

       Block block = blockService.createBlock(pendingTransactions,lastBlock.getMy_hash());

       Block minedBlock = blockService.mineBlock(blockchain.getDifficulté(), block);
       // add the mined block to the blockchain
       blockchain.getBlocks().add(minedBlock);

       blockChainRepository.save(blockchain);

   }

    @Override
    public Block getLastBlock(String blockchainID) {
         Blockchain blockchain = blockChainRepository.findById(blockchainID).get();
        if(blockchain == null)
            return null;
        return blockchain.getBlocks().get(blockchain.getBlocks().size()-1);
    }

    @Override
    public Boolean isValid(String blockchainID) {
        Blockchain blockchain = blockChainRepository.findById(blockchainID).get();
        if(blockchain == null) return false;

        Block previosBlock = null;
        for (Block block :blockchain.getBlocks())
        {
            if(!block.getMy_hash().equals(((BlockServiceImpl)blockService).getThePerfectHash(blockchain.getDifficulté(),block)))
            {
                System.out.println("broblem in current hash");
                System.out.println("block hash: "+block.getMy_hash());
                System.out.println("calculated hash: "+blockService.calculateHash(block));



                return false;
            }

            if(previosBlock!= null && !previosBlock.getMy_hash().equals(block.getPres_hash()))
            {
                System.out.println("broblem in previous hash");
                System.out.println("current pres hash:"+ block.getPres_hash());
                System.out.println("pres block hash:"+ previosBlock.getMy_hash());
                return false;

            }
            previosBlock = block;
        }

        return true;
    }

    @Override
    public double getAddressSolde(String blockchainID,String addr) {
        Blockchain blockchain = blockChainRepository.findById(blockchainID).get();

        double solde = 0;

        for (Block block : blockchain.getBlocks())
        {
            for (Transaction transaction : block.getTransactions())
            {
                if(transaction.getDes_adr().equals(addr))
                    solde+=transaction.getMontant();
            }
        }
        return solde;
    }

    @Override
    public Blockchain getBlockchaineByID(String id) throws BlockChaineNotFoundException {
        Blockchain blockchain = blockChainRepository.findById(id).get();
        if(blockchain == null) throw  new BlockChaineNotFoundException("blockchaine does not exist !");

        return blockchain;
    }

}
