package com.BillManagementSystems.Service;

import com.BillManagementSystems.Model.Product;
import com.BillManagementSystems.Model.Stock;
import com.BillManagementSystems.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    ProductService productService;

    public Stock addStock(Stock stock){
        return stockRepository.save(stock);
    }

    public boolean existOrNot(int stockId){
        return stockRepository.existsById(stockId);
    }
    public Stock updateThroughProIdAndStockId(int productId, int stockId,Stock stock) {
        Stock old=stockRepository.findById(stockId).get();
        Product p=productService.getByProductId(productId);
        old.setProduct(p);
//        old.setStockId(stock.getStockId());
        old.setStockQuantity(old.getStockQuantity()+stock.getStockQuantity());
        old.setThresholdLevel(stock.getThresholdLevel());
        return stockRepository.save(old);
    }
}
