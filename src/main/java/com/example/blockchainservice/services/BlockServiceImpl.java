package com.example.blockchainservice.services;


import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BlockServiceImpl implements BlockService{
    @Override
    public Block createBlock(List<Transaction> transactions,String prevHash ) {
        Block newBlock = new Block();
        newBlock.setCreated_at(new Date());
        newBlock.setPres_hash(prevHash);
        newBlock.setTransactions(transactions);
        newBlock.setMy_hash(calculateHash(newBlock));

        return newBlock;
    }



    @Override
    public String calculateHash(Block block) {
        String dataToHash =  block.getPres_hash() + block.getCreated_at().toString() + block.getTransactions().get(0).hashCode() + Integer.toString(block.getNonce());

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

    @Override
    public Block mineBlock(int difficulty,Block block) {

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

        // return block with new hash
        return block;
    }
}
