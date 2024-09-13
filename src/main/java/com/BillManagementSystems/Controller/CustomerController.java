package com.BillManagementSystems.Controller;

import com.BillManagementSystems.Model.Customer;
import com.BillManagementSystems.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public List<Customer> addAllCustomers(@RequestBody List<Customer> customers){
        return customerService.saveAllCustomer(customers);
    }
    @GetMapping("/get")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomer();
    }
}
