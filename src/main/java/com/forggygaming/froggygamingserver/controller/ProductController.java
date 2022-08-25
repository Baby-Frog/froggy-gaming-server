package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.Product;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.form.AddProductToBrandForm;
import com.forggygaming.froggygamingserver.form.AddProductToCategoryForm;
import com.forggygaming.froggygamingserver.form.AddProductToOrderDetailForm;
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

    @PostMapping("/add-to-category")
    public ResponseEntity<ResponseObject> addtocategory(@RequestBody AddProductToCategoryForm form) {
        return productServices.addToCategory(form);
    }

    @PostMapping("/add-to-brand")
    public ResponseEntity<ResponseObject> addToBrand(@RequestBody AddProductToBrandForm form) {
        return productServices.addToBrand(form);
    }

    @PostMapping("/add-to-order-detail")
    public ResponseEntity<ResponseObject> addProductToOrderDetail(@RequestBody AddProductToOrderDetailForm form) {
        return productServices.addToOrderDetail(form);
    }

    @PostMapping("/delete-product-detail/{id}")
    public ResponseEntity<ResponseObject> removeProductDetail(@PathVariable Long id, @RequestBody DeleteProductDetailInProductForm form) {
        return productServices.removeProductDetail(id, form.getProductDetailId());
    }

    @GetMapping("/search/query={proName}")
    public ResponseEntity<ResponseObject> searchProductsByProName(@PathVariable String proName) {
        return productServices.searchProductsByProName(proName);
    }

    @GetMapping("/sort-by-name/asc")
    public ResponseEntity<ResponseObject> ascSortProductsByProName() {
        return productServices.ascSortProductByProName();
    }

    @GetMapping("/sort-by-name/desc")
    public ResponseEntity<ResponseObject> descSortProductsByProName() {
        return productServices.descSortProductByProName();
    }

    @GetMapping("/sort-by-price/asc")
    public ResponseEntity<ResponseObject> ascSortProductsByProPrice() {
        return productServices.ascSortProductByProPrice();
    }

    @GetMapping("/sort-by-price/desc")
    public ResponseEntity<ResponseObject> descSortProductsByProPrice() {
        return productServices.descSortProductByProPrice();
    }

    @GetMapping("/sort-by-date/asc")
    public ResponseEntity<ResponseObject> ascSortProductsByCreatedAt() {
        return productServices.ascSortProductsByCreatedAt();
    }

    @GetMapping("/sort-by-date/desc")
    public ResponseEntity<ResponseObject> descSortProductsByCreatedAt() {
        return productServices.descSortProductsByCreatedAt();
    }

    @GetMapping("/min-price={proPriceMin}&max-price={proPriceMax}")
    public ResponseEntity<ResponseObject> getProductsInProPriceZone(@PathVariable Double proPriceMin, @PathVariable Double proPriceMax) {
        return productServices.getProductsInProPriceZone(proPriceMin, proPriceMax);
    }
}
