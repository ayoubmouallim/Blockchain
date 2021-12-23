package com.example.blockchainservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class TransactionDto {
    private Date transaction_date;
    private String src_adr;
    private String des_adr;
    private double montant;
}
