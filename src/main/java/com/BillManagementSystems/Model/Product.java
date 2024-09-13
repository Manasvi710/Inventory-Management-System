package com.BillManagementSystems.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @Column(name = "product_id")
    private int productId;
    private String productName;
    private long productStock;
    private double productPrice;
    private double gst;

    @JsonIgnore
    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    private List<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Stock> stocks;
}
