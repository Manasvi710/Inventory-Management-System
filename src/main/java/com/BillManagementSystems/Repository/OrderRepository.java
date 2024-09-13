package com.BillManagementSystems.Repository;

import com.BillManagementSystems.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT * FROM `order` WHERE customer_id =:customerId AND product_id =:productId;", nativeQuery = true)
    List<Order> getAllOrdersByProductIdAndCustomerId(@Param("customerId") int customerId, @Param("productId") int productId);
}
