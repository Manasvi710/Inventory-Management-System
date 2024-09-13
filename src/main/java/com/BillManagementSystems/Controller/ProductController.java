package com.BillManagementSystems.Controller;

import com.BillManagementSystems.Model.Product;
import com.BillManagementSystems.Model.Stock;
import com.BillManagementSystems.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
   private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> saveAllProducts(@RequestBody List<Product> products){
        try {
            return new ResponseEntity<>(productService.saveAllProduct(products), HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get/{proId}/{stId}")
    public ResponseEntity<?> getStockOfProductId(@PathVariable int proId, @PathVariable int stId){
        try {
            if(productService.exitOrNot(proId) != false) {
                return new ResponseEntity<>(productService.getAllstocThroughProId(proId, stId), HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("Check Your Id", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getListOfProduct(){
        try {
            return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.FOUND);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("get/{proId}")
    public ResponseEntity<?> getProductById(@PathVariable int proId){
        try {
            return new ResponseEntity<>(productService.getByProductId(proId), HttpStatus.FOUND);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
