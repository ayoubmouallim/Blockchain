package com.example.blockchainservice.web;

import com.example.blockchainservice.dtos.BlockDto;
import com.example.blockchainservice.dtos.BlockchaineDto;
import com.example.blockchainservice.dtos.SoldRequest;
import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Blockchain;
import com.example.blockchainservice.entities.Transaction;
import com.example.blockchainservice.exceptions.BlockChaineNotFoundException;

import com.example.blockchainservice.services.BlockChainService;
import com.example.blockchainservice.services.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TransferQueue;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blockchaine")
@CrossOrigin(origins = "*"  )
public class BlockChainController {

    @Autowired
    BlockChainService blockChainService;

    @PostMapping("/create")
    public Blockchain createBlockChaine(@RequestBody BlockchaineDto blockchaineDto)
    {
        return blockChainService.createBlockChain(blockchaineDto.getName(),blockchaineDto.getDifficulty(),blockchaineDto.getReward());
    }

    @GetMapping("/{id}")
    public  Blockchain getBlockchaine(@PathVariable String id) throws BlockChaineNotFoundException {
        return blockChainService.getBlockchaineByID(id);
    }

    @GetMapping("/all")
    public  List<Blockchain> getAllBlockchain() throws BlockChaineNotFoundException {
        return blockChainService.getAllBlochChain();
    }

    @PostMapping("mine")
    public void mineBlock(@RequestBody BlockDto blockDto)
    {
        List<Transaction> transactions = blockDto.getTransactions().stream().map(trDto ->new Transaction(
            null,new Date(),trDto.getSrc_adr(),trDto.getDes_adr(),trDto.getMontant()) ).collect(Collectors.toList());
        blockChainService.mineBlock(blockDto.getBlockchainID(),blockDto.getMin_adr(),transactions);
    }

    @GetMapping("/lastblock/{id}")
    public Block LastBlock(@PathVariable String id)
    {
        return blockChainService.getLastBlock(id);
    }

    @GetMapping("/isvalid/{id}")
    public Boolean isValid(@PathVariable String id)
    {
        return blockChainService.isValid(id);
    }

    @PostMapping("/solde")
    public double getSolde(@RequestBody SoldRequest soldRequest)
    {
        return blockChainService.getAddressSolde(soldRequest.getBlockchaineId(),soldRequest.getAddress());
    }


    @ExceptionHandler(BlockChaineNotFoundException.class)
    public ResponseEntity<String> noBlockchaine(Exception ex){
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
