package com.example.blockchainservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blockchain {
    @Id
    private String id;
    private String nom;
    private int difficulté; // (entier utilisé au moment du minage),
    private double miningReward; // (récompense attribuée au mineur du block).
    @OneToMany
    private List<Block> blocks = new LinkedList<>();



}
