package com.BillManagementSystems.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.domain.Sort;

import java.util.Date;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer paymentId;
    private Date paymentDate;
    private Double paymentAmount;
    private String paymentConfirmation;

    @OneToOne
    @JoinColumn(name ="orderId")
    private Order order;
}
