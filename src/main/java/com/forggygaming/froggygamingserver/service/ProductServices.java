package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.entity.Product;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServices {
    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public ResponseEntity<ResponseObject> saveNewProduct(Product product) {
        Product exists = productRepository.findProductByName(product.getName());
        return exists == null
                ? ResponseEntity.ok()
                .body(new ResponseObject("OK", "Save new product successfully", productRepository.save(product)))
                : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(new ResponseObject("FALSE", "This product is exists !", productRepository.findProductByName(product.getName())));
    }

    public ResponseEntity<ResponseObject> deleteProductById(Long id) {
        Optional<Product> exists = productRepository.findById(id);
        if (exists.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().body(new ResponseObject("OK", "Delete successfully", productRepository.findById(id)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseObject("FALSE", "This category is not exists !!", id));
    }
}
