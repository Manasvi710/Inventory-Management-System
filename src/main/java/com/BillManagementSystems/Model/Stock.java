package com.BillManagementSystems.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int stockId;


    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;


    private int stockQuantity;
    private int thresholdLevel;
}
