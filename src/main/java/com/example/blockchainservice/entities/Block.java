package com.example.blockchainservice.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Block {

    @Id
    private String id;
    private Date created_at;
    private String my_hash;
    private String pres_hash;
    @OneToMany(mappedBy = "block" ,cascade =CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();
    private int  nonce; // (un entier utilisé au moment du minage des blocks).



}
