package com.BillManagementSystems.Service;

import com.BillManagementSystems.Model.Customer;
import com.BillManagementSystems.Repository.CustomerRepository;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public List<Customer> saveAllCustomer(List<Customer> customers){
        return customerRepository.saveAll(customers);
    }

    public boolean exsitOrNot(int custid) {
        return customerRepository.existsById(custid);
    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }
    public Customer getByCustomerId(int customerId){
        return customerRepository.findById(customerId).get();
    }
}
