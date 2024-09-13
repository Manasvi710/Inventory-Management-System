package com.BillManagementSystems.Service;

import com.BillManagementSystems.Model.Bill;
import com.BillManagementSystems.Model.Order;
import com.BillManagementSystems.Model.Payment;
import com.BillManagementSystems.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private OrderService orderService;

    private BillService billService;
    @Autowired
    PaymentRepository paymentRepository;

    public void savePayment(Payment payment){
        paymentRepository.save(payment);
    }

    public Payment getByOrderId(Integer orderId){
        return paymentRepository.getDetailsByOrderId(orderId);
    }

    public Payment addPayment(Payment payment, Integer orderId){
        Order o=orderService.getOrderByOrderId(orderId);
        o.setPaidOrNot(true);
        Bill b=o.getBill();
        b.setPaidOrNot(true);
        orderService.saveOrder(o);
        billService.saveBill(b);
        payment.setOrder(o);
        if(b.getPrice()==payment.getPaymentAmount()){
           return paymentRepository.save(payment);
        }else{
            return null;
        }
    }
}
