package com.BillManagementSystems.Controller;

import com.BillManagementSystems.Model.Payment;
import com.BillManagementSystems.Service.OrderService;
import com.BillManagementSystems.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @Autowired
    OrderService orderService;
    @PostMapping("/save")
    public ResponseEntity<?> addPayment(@RequestBody Payment payment, @PathVariable Integer orderId) {
        try {
            if(orderService.existOrNot(orderId)!=false){
                return new ResponseEntity<>(paymentService.addPayment(payment, orderId), HttpStatus.FOUND);
            }else {
                return new ResponseEntity<>("check your orderId", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllDetails(@PathVariable Integer orderId){
        try {
            if(orderService.existOrNot(orderId)!=false){
                return new ResponseEntity<>(paymentService.getByOrderId(orderId),HttpStatus.FOUND);
            }else {
                return new ResponseEntity<>("Check your OrderId", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
