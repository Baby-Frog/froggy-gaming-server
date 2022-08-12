package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.Product;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.service.ProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServices productServices;

    @GetMapping
    public List<Product> getProducts() {
        return productServices.getProducts();
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseObject> saveNewProduct(@RequestBody Product product) {
        return productServices.saveNewProduct(product);
    }
}
