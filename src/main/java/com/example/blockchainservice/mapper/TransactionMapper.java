package com.example.blockchainservice.mapper;

import com.example.blockchainservice.dtos.BlockDto;
import com.example.blockchainservice.dtos.TransactionDto;
import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

        Transaction TransactionDtoToTransaction(TransactionDto transactionDto);


}
