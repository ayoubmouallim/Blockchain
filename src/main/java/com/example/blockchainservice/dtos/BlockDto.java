package com.example.blockchainservice.dtos;

import com.example.blockchainservice.entities.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class BlockDto {
    private String blockchainID;
    private String pres_hash;
    private String min_adr;
    private List<TransactionDto> transactions = new ArrayList<>();
}
