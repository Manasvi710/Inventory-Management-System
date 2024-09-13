package com.BillManagementSystems.Service;

import com.BillManagementSystems.Model.Product;
import com.BillManagementSystems.Model.Stock;
import com.BillManagementSystems.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public List<Product> saveAllProduct(List<Product> products){
        return productRepository.saveAll(products);
    }
    public Product getByProductId(int productId){
        return productRepository.findById(productId).get();
    }
    public List<Stock> getAllstocThroughProId(int proId, int stId) {
        List<Object[]>objectList=productRepository.getAllStockByProidAndStockId(proId,stId);
        List<Stock> stockList = new ArrayList<>();
        for (Object[] objArray : objectList) {
            Stock stock = new Stock();
            stock.setStockId((Integer) objArray[0]);
            stock.setStockQuantity((Integer) objArray[2]);
            stock.setThresholdLevel((Integer) objArray[3]);
            stockList.add(stock);
        }
        return stockList;
    }
    public boolean exitOrNot(int productId){
        return productRepository.existsById(productId);
    }
}
