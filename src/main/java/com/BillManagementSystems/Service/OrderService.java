package com.BillManagementSystems.Service;

import ch.qos.logback.classic.Logger;
import com.BillManagementSystems.Model.*;
import com.BillManagementSystems.Repository.BillRepository;
import com.BillManagementSystems.Repository.CustomerRepository;
import com.BillManagementSystems.Repository.OrderRepository;
import com.BillManagementSystems.Repository.ProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BillRepository billRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    @Qualifier("SMSservices")
     private SMSservices smSservices;

    @Autowired
    StockService stockService;


    public List<Order>getAllOrders(){
        return orderRepository.findAll();
    }
    public boolean existOrNot(int orderId){
        return orderRepository.existsById(orderId);
    }

    public Order getOrderByOrderId(Integer orderId){
        return orderRepository.findById(orderId).get();
    }

    public List<Order> getAllorderswithproidandcustid(int proId, int custId) {
        return orderRepository.getAllOrdersByProductIdAndCustomerId(proId, custId);
    }

    public String saveAllOrder(int customerId, Order order, int productId) {
        if (productRepository.existsById(productId)==false)
            return "Product not found..";

        Customer customer=customerService.getByCustomerId(customerId);
        Product product=productRepository.findById(productId).get();
        Order o=new Order();
        o.setProduct(product);
        o.setPaidOrNot(o.isPaidOrNot());
                Bill bill = new Bill();
                bill.setPrice(product.getProductPrice() * order.getProductQuantity());
                product.setProductStock(product.getProductStock()-order.getProductQuantity());
                bill.setGst(product.getGst());

                double totalPrice = product.getProductPrice() * order.getProductQuantity();
                double GstAmount = totalPrice * product.getGst() / 100;
                bill.setFinalPrice(totalPrice + GstAmount);
                bill.setOrder(order);
                bill.setPaidOrNot(order.isPaidOrNot());
                order.setCustomer(customerRepository.findById(customerId).get());
                order.setProduct(product);
                orderRepository.save(order);
                billRepository.save(bill);
        if(order.isPaidOrNot()==true){
            Payment p=new Payment();
            p.setOrder(order);
            p.setPaymentAmount(bill.getPrice());
            p.setPaymentDate(Date.valueOf(LocalDate.now()));
            p.setPaymentConfirmation("paid");
            paymentService.savePayment(p);
//            smSservices.sendSMS(customerId, order, productId);
//            smSservices.sendonwhatsapp(customerId, order, productId);
//            smSservices.sendEmail("manasvimangrola@gmail.com", "quantity manage", "Order will Placed but your payment is pending..");
        }else{
//            smSservices.sendSMSPayment(customerId, order, productId);
//            smSservices.sendonwhatsappPayment(customerId, order, productId);
//            smSservices.sendEmail("manasvimangrola@gmail.com", "quantity manage", "Order will Placed your payment is done..");
        }

        return "order success";
    }
    public void saveOrder(Order order){
        orderRepository.save(order);
    }
}