package com.example.blockchainservice.services;


import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Transaction;
import com.example.blockchainservice.repository.BlockRepository;
import com.example.blockchainservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BlockServiceImpl implements BlockService{

    @Autowired
    private BlockRepository blockRepository;
    @Autowired private TransactionRepository transactionRepository;

    @Override
    public Block createBlock(List<Transaction> transactions,String prevHash ) {
        Block newBlock = new Block();
        newBlock.setId(UUID.randomUUID().toString());
        newBlock.setCreated_at(new Date());
        newBlock.setPres_hash(prevHash);
        newBlock.setMy_hash(calculateHash(newBlock));
        //newBlock.setTransactions(transactions);
        blockRepository.save(newBlock);

        if(transactions != null)
        {
            for(Transaction transaction:transactions)
            {
                transaction.setBlock(newBlock);
            }
            transactionRepository.saveAll(transactions);
        }


        return newBlock;
    }



    @Override
    public String calculateHash(Block block) {

        String dataToHash =  block.getPres_hash() + block.getCreated_at().toString()  + Integer.toString(block.getNonce());

        if(block.getTransactions() != null)
            dataToHash+= block.getTransactions().hashCode();

        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    public String getThePerfectHash(int difficulty,Block block)
    {
        // if difficulty = 4 ===> difficultyString = "0000"
        String difficultyString = new String(new char[difficulty]).replace('\0', '0');
        String hash = block.getMy_hash();
        while (!hash.substring(0, difficulty).equals(difficultyString)) {
            block.setNonce(block.getNonce()+1);
            // recalculate
            hash = calculateHash(block);
        }
        System.out.println("found hash: "+hash);
        block.setMy_hash(hash);
    return hash;
    }

    @Override
    public Block mineBlock(int difficulty,Block block) {

        getThePerfectHash(difficulty,block);

        // return block with new hash
        blockRepository.save(block);

        return block;
    }

    @Override
    public List<Block> getAllBlocks() {
        return blockRepository.findAll();
    }
}
