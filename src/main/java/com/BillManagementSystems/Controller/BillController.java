package com.BillManagementSystems.Controller;

import com.BillManagementSystems.Model.Bill;
import com.BillManagementSystems.Service.BillService;
import com.BillManagementSystems.Service.CustomerService;
import com.BillManagementSystems.Service.OrderService;
import com.BillManagementSystems.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @Autowired
    BillService billService;

    @Autowired
    OrderService orderService;

    @GetMapping("/get")
    public List<Bill> getAllBill(@PathVariable int orderId){
        return billService.getByOrderId(orderId);
    }
}
