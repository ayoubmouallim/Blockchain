package com.example.blockchainservice.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date transaction_date;
    private String src_adr;
    private String des_adr;
    private double montant;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Block block;

    public Transaction(Long id, Date transaction_date, String src_adr, String des_adr, double montant) {
        this.id = id;
        this.transaction_date = transaction_date;
        this.src_adr = src_adr;
        this.des_adr = des_adr;
        this.montant = montant;
    }
}
