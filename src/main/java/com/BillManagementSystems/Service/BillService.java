package com.BillManagementSystems.Service;

import com.BillManagementSystems.Model.Bill;
import com.BillManagementSystems.Repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    @Autowired
    BillRepository billRepository;

    public void saveBill(Bill bill){
        billRepository.save(bill);
    }
    public List<Bill> getByOrderId(int orderId) {
        return billRepository.getByOrderId(orderId);
    }

    public Bill getBill(Integer billId) {
        return billRepository.findById(billId).get();
    }

}
