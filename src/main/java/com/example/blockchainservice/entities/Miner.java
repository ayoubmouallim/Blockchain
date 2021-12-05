package com.example.blockchainservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Miner {
    @Id
    private String id;
    private String add;
    @OneToOne
    private Transaction bonus;

}
