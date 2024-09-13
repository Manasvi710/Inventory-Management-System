package com.BillManagementSystems.Controller;

import com.BillManagementSystems.Model.Order;
import com.BillManagementSystems.Service.CustomerService;
import com.BillManagementSystems.Service.OrderService;
import com.BillManagementSystems.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

 private   OrderService orderService;
 @Autowired
 ProductService productService;
 @Autowired
 CustomerService customerService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save/{customerId}/{productId}")
    public String saveOrder (@PathVariable int customerId,@RequestBody Order order,@PathVariable int productId){
        if(productService.exitOrNot(productId)!= false && customerService.exsitOrNot(customerId)!=false){
            String str= orderService.saveAllOrder(customerId,order,productId);
            return str;
        }else {
            return "check your id";
        }
    }
    @GetMapping("/get")
    public List<Order> getAllOrder(@PathVariable int productId,@PathVariable int customerId){
        return orderService.getAllorderswithproidandcustid(productId, customerId);
    }
}
