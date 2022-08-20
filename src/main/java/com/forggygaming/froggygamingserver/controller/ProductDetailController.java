package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.ProductDetail;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.form.AddProductDetailToProductForm;
import com.forggygaming.froggygamingserver.service.ProductDetailServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/productdetails")
@RequiredArgsConstructor
public class ProductDetailController {
    private final ProductDetailServices productDetailServices;

    @GetMapping
    public List<ProductDetail> getProductDetails() {
        return productDetailServices.getProductDetails();
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseObject> saveNewProductDetail(@RequestBody ProductDetail productDetail) {
        return productDetailServices.saveNewProductDetail(productDetail);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteProductDetailById(@PathVariable Long id) {
        return productDetailServices.deleteProductDetailById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateProductDetailById(@PathVariable Long id, @RequestBody ProductDetail productDetail) {
        return productDetailServices.updateProductDetailById(id, productDetail);
    }

    @PostMapping("/addtoproduct")
    private ResponseEntity<ResponseObject> addProductDetailToProductById(@RequestBody AddProductDetailToProductForm form) {
        return productDetailServices.addProductDetailToProductById(form);
    }
}
