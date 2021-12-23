package com.example.blockchainservice;

import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Blockchain;
import com.example.blockchainservice.entities.Transaction;
import com.example.blockchainservice.repository.BlockChainRepository;
import com.example.blockchainservice.repository.BlockRepository;
import com.example.blockchainservice.repository.TransactionRepository;
import com.example.blockchainservice.services.BlockService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BlockchainServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockchainServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(TransactionRepository transactionRepository, BlockRepository blockRepository,
                            BlockChainRepository blockchainRepository, BlockService blockService) {
        return args -> {
           /* System.out.println("********* start ************");
            Transaction tr1 = transactionRepository.save(new Transaction("1",new Date(),"AAA123","BBB123",112));
            Transaction tr2 = transactionRepository.save(new Transaction("2",new Date(),"AZE23","AQW123",95));

            List<Transaction> transactions = new ArrayList<>();
            transactions.add(tr1);
            transactions.add(tr2);

            Blockchain blockchain = blockchainRepository.save(new Blockchain());

            Block firstBlock = blockRepository.save(new Block("1", new Date(), "0000321654", "", transactions,0));

            Block nextBlock = blockService.createBlock(transactions,firstBlock.getMy_hash());
            System.out.println("nextBlock: "+nextBlock.getMy_hash());
            Block lastBlock = blockService.mineBlock(4,nextBlock);

            blockchain.getBlocks().add(firstBlock);
            blockchain.getBlocks().add(nextBlock);
            blockchain.getBlocks().add(lastBlock);

            System.out.println("lastBlock hash: "+lastBlock.getMy_hash());
            System.out.println("nonce: "+lastBlock.getNonce());

            */
        };
    }




}
