package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.Product;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.form.AddProductToBrandForm;
import com.forggygaming.froggygamingserver.form.AddProductToCategoryForm;
import com.forggygaming.froggygamingserver.form.DeleteProductDetailInProductForm;
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteProductById(@PathVariable Long id) {
        return productServices.deleteProductById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateProductById(@PathVariable Long id, @RequestBody Product product) {
        return productServices.updateProductById(id, product);
    }

    @PostMapping("/addtocategory")
    public ResponseEntity<ResponseObject> addtocategory(@RequestBody AddProductToCategoryForm form) {
        return productServices.addToCategory(form);
    }

    @PostMapping("/addtobrand")
    public ResponseEntity<ResponseObject> addToBrand(@RequestBody AddProductToBrandForm form) {
        return productServices.addToBrand(form);
    }

    @PostMapping("/deleteproductdetail/{id}")
    public ResponseEntity<ResponseObject> removeProductDetail(@PathVariable Long id, @RequestBody DeleteProductDetailInProductForm form) {
        return productServices.removeProductDetail(id, form.getProductDetailId());
    }
}
