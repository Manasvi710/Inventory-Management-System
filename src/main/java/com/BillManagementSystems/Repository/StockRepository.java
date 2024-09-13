package com.BillManagementSystems.Repository;

import com.BillManagementSystems.Model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
}
