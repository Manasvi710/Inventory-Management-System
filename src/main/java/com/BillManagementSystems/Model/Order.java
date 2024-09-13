package com.BillManagementSystems.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
    private Date orderDate;
    private int productQuantity;
    private String area;
    private String society;
    private int houseNumber;
    private String city;
    private String country;
    private int pinCode;
    private boolean paidOrNot;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @JsonIgnore
    @OneToOne(mappedBy = "order", fetch = FetchType.EAGER)
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @JsonIgnore
    @OneToOne(mappedBy = "order", fetch = FetchType.EAGER)
    private Payment payment;

}
