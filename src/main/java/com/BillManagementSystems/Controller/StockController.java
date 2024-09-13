package com.BillManagementSystems.Controller;

import com.BillManagementSystems.Model.Stock;
import com.BillManagementSystems.Service.ProductService;
import com.BillManagementSystems.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    StockService stockService;

    @Autowired
    ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> saveStock(@RequestBody Stock stock){
        try {
            return new ResponseEntity<>(stockService.addStock(stock), HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/addStock/{productId}/{stockId}")
    public ResponseEntity<?> addStockUpdate(@PathVariable int productId, @PathVariable int stockId, @RequestBody Stock stock){
        try {
            if(productService.exitOrNot(productId)!=false && stockService.existOrNot(stockId)!=false){
                return new ResponseEntity<>(stockService.updateThroughProIdAndStockId(productId, stockId, stock), HttpStatus.OK);
            }
            return new ResponseEntity<>("Check your Id", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR) ;
    }

}
