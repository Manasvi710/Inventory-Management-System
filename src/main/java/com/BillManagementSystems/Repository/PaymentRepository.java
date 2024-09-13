package com.BillManagementSystems.Repository;

import com.BillManagementSystems.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Query(value = "select * from payment where order_id=:orderId", nativeQuery = true)
    Payment getDetailsByOrderId(@Param("orderId") Integer orderId);
}
