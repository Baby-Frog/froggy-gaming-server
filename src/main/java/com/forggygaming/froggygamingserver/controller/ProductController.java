package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.Product;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.form.AddProductToBrandForm;
import com.forggygaming.froggygamingserver.form.AddProductToCategoryForm;
import com.forggygaming.froggygamingserver.form.AddProductToOrderDetailForm;
import com.forggygaming.froggygamingserver.form.DeleteProductDetailInProductForm;
import com.forggygaming.froggygamingserver.service.ProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServices productServices;

    @GetMapping("/page={page}")
    public Page<Product> getProducts(@PathVariable Integer page) {
        return productServices.getProducts(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable Long id) {
        return productServices.getProduct(id);
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

    @GetMapping("/sort=pro.name&order=asc")
    public ResponseEntity<ResponseObject> ascSortProductsByProName() {
        return productServices.ascSortProductByProName();
    }

    @GetMapping("/sort=pro.name&order=desc")
    public ResponseEntity<ResponseObject> descSortProductsByProName() {
        return productServices.descSortProductByProName();
    }

    @GetMapping("/sort=pro.price&order=asc")
    public ResponseEntity<ResponseObject> ascSortProductsByProPrice() {
        return productServices.ascSortProductByProPrice();
    }

    @GetMapping("/sort=pro.price&order=desc")
    public ResponseEntity<ResponseObject> descSortProductsByProPrice() {
        return productServices.descSortProductByProPrice();
    }

    @GetMapping("/sort=pro.date&order=asc")
    public ResponseEntity<ResponseObject> ascSortProductsByCreatedAt() {
        return productServices.ascSortProductsByCreatedAt();
    }

    @GetMapping("/sort=pro.date&order=desc")
    public ResponseEntity<ResponseObject> descSortProductsByCreatedAt() {
        return productServices.descSortProductsByCreatedAt();
    }

    @GetMapping("/min-price={proPriceMin}&max-price={proPriceMax}")
    public ResponseEntity<ResponseObject> getProductsInProPriceZone(@PathVariable Double proPriceMin, @PathVariable Double proPriceMax) {
        return productServices.getProductsInProPriceZone(proPriceMin, proPriceMax);
    }

    @GetMapping("/search/query={proName}&sort=pro.price&order=asc")
    public ResponseEntity<ResponseObject> searchProductsByNameAndAscSortByPrice(@PathVariable String proName) {
        return productServices.searchProductsByNameAndAscSortByPrice(proName);
    }

    @GetMapping("/search/query={proName}&sort=pro.price&order=desc")
    public ResponseEntity<ResponseObject> searchProductsByNameAndDescSortByPrice(@PathVariable String proName) {
        return productServices.searchProductsByNameAndDescSortByPrice(proName);
    }

    @GetMapping("/search/query={proName}&sort=pro.date&order=asc")
    public ResponseEntity<ResponseObject> searchProductsByNameAndAscSortByDate(@PathVariable String proName) {
        return productServices.searchProductsByNameAndAscSortByDate(proName);
    }

    @GetMapping("/search/query={proName}&sort=pro.date&order=desc")
    public ResponseEntity<ResponseObject> searchProductsByNameAndDescSortByDate(@PathVariable String proName) {
        return productServices.searchProductsByNameAndDescSortByDate(proName);
    }
    @GetMapping("/search/query={proName}&sort=pro.name&order=asc")
    public ResponseEntity<ResponseObject> searchProductsByNameAndAscSortByName(@PathVariable String proName) {
        return productServices.searchProductsByNameAndAscSortByName(proName);
    }
    @GetMapping("/search/query={proName}&sort=pro.name&order=desc")
    public ResponseEntity<ResponseObject> searchProductsByNameAndDescSortByName(@PathVariable String proName) {
        return productServices.searchProductsByNameAndDescSortByName(proName);
    }

    @GetMapping("/products&cate-id={cateId}")
    public ResponseEntity<ResponseObject> getProductByCateId(@PathVariable Long cateId) {
        return productServices.getProductsByCateId(cateId);
    }

    @GetMapping("/products&brand-id={brandId}")
    public ResponseEntity<ResponseObject> getProductByBrandId(@PathVariable Long brandId) {
        return productServices.getProductsByBrandId(brandId);
    }
}
