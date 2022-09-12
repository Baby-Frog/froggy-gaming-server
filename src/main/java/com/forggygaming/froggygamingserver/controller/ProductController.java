package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.Product;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.form.AddProductToBrandForm;
import com.forggygaming.froggygamingserver.form.AddProductToCategoryForm;
import com.forggygaming.froggygamingserver.form.AddProductToOrderDetailForm;
import com.forggygaming.froggygamingserver.service.ProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServices productServices;

    @PermitAll
    @GetMapping("/page={page}")
    public ResponseEntity<ResponseObject> getProducts(@PathVariable Integer page) {
        return productServices.getProducts(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable Long id) {
        return productServices.getProduct(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> saveNewProduct(@RequestBody Product product) {
        return productServices.saveNewProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> deleteProductById(@PathVariable Long id) {
        return productServices.deleteProductById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> updateProductById(@PathVariable Long id, @RequestBody Product product) {
        return productServices.updateProductById(id, product);
    }

    @PostMapping("/add-to-category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> addtocategory(@RequestBody AddProductToCategoryForm form) {
        return productServices.addToCategory(form);
    }

    @PostMapping("/add-to-brand")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> addToBrand(@RequestBody AddProductToBrandForm form) {
        return productServices.addToBrand(form);
    }

    @PostMapping("/add-to-order-detail")
    public ResponseEntity<ResponseObject> addToOrderDetail(@RequestBody AddProductToOrderDetailForm form) {
        return productServices.addToOrderDetail(form);
    }

    @GetMapping("/search/query={proName}&page={page}")
    public ResponseEntity<ResponseObject> searchProductsByProName(@PathVariable String proName, @PathVariable Integer page) {
        return productServices.searchProductsByProName(proName, page);
    }

    @GetMapping("/search/query={proName}&page={page}/sort=pro.price&order=asc")
    public ResponseEntity<ResponseObject> searchProductsByProNameAndAscSortByPrice(@PathVariable String proName, @PathVariable Integer page) {
        return productServices.searchProductsByProNameAndAscSortByPrice(proName, page);
    }

    @GetMapping("/search/query={proName}&page={page}/sort=pro.price&order=desc")
    public ResponseEntity<ResponseObject> searchProductsByProNameAndDescSortByPrice(@PathVariable String proName, @PathVariable Integer page) {
        return productServices.searchProductsByProNameAndDescSortByPrice(proName, page);
    }

    @GetMapping("/search/query={proName}&page={page}/sort=pro.name&order=asc")
    public ResponseEntity<ResponseObject> searchProductsByProNameAndAscSortByName(@PathVariable String proName, @PathVariable Integer page) {
        return productServices.searchProductsByProNameAndAscSortByName(proName, page);
    }

    @GetMapping("/search/query={proName}&page={page}/sort=pro.name&order=desc")
    public ResponseEntity<ResponseObject> searchProductsByProNameAndDescSortByName(@PathVariable String proName, @PathVariable Integer page) {
        return productServices.searchProductsByProNameAndDescSortByName(proName, page);
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
    public ResponseEntity<ResponseObject> getProductsByCateId(@PathVariable Long cateId) {
        return productServices.getProductsByCateId(cateId);
    }

    @GetMapping("/products&brand-id={brandId}")
    public ResponseEntity<ResponseObject> getProductsByBrandId(@PathVariable Long brandId) {
        return productServices.getProductsByBrandId(brandId);
    }

    @PostMapping("/{proId}/remove/image-id={imgId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> removeImageInProduct(@PathVariable Long proId, @PathVariable Long imgId) {
        return productServices.removeImageInProduct(proId, imgId);
    }

    @GetMapping("/search/query={proName}&page={page}/cate-id={cateId}")
    public ResponseEntity<ResponseObject> searchProductsByNameAndCategoryId(@PathVariable String proName, @PathVariable Integer page, @PathVariable Long cateId) {
        return productServices.searchProductsByNameAndCategoryId(proName, page, cateId);
    }

    @GetMapping("/search/query={proName}&page={page}/cate-id={cateId}&sort=pro.price&order=asc")
    public ResponseEntity<ResponseObject> searchProductsByNameAndCategoryIdAscSortByProPrice(@PathVariable String proName, @PathVariable Integer page, @PathVariable Long cateId) {
        return productServices.searchProductsByNameAndCategoryIdAscSortByProPrice(proName, page, cateId);
    }

    @GetMapping("/search/query={proName}&page={page}/cate-id={cateId}&sort=pro.price&order=desc")
    public ResponseEntity<ResponseObject> searchProductsByNameAndCategoryIdDescSortByProPrice(@PathVariable String proName, @PathVariable Integer page, @PathVariable Long cateId) {
        return productServices.searchProductsByNameAndCategoryIdDescSortByProPrice(proName, page, cateId);
    }

    @GetMapping("/search/query={proName}&page={page}/cate-id={cateId}&sort=pro.name&order=asc")
    public ResponseEntity<ResponseObject> searchProductsByNameAndCategoryIdAscSortByProName(@PathVariable String proName, @PathVariable Integer page, @PathVariable Long cateId) {
        return productServices.searchProductsByNameAndCategoryIdAscSortByProName(proName, page, cateId);
    }

    @GetMapping("/search/query={proName}&page={page}/cate-id={cateId}&sort=pro.name&order=desc")
    public ResponseEntity<ResponseObject> searchProductsByNameAndCategoryIdDescSortByProName(@PathVariable String proName, @PathVariable Integer page, @PathVariable Long cateId) {
        return productServices.searchProductsByNameAndCategoryIdDescSortByProName(proName, page, cateId);
    }

    @GetMapping("/search/query={proName}&page={page}/min-price={proPriceMin}&max-price={proPriceMax}")
    public ResponseEntity<ResponseObject> searchProductsByNameInProPriceZone(@PathVariable String proName, @PathVariable Integer page, @PathVariable Long proPriceMin, @PathVariable Long proPriceMax) {
        return productServices.searchProductsByNameInProPriceZone(proName, page, proPriceMin, proPriceMax);
    }

    @GetMapping("/search/query={proName}&page={page}&sort=pro.price&order=asc/min-price={proPriceMin}&max-price={proPriceMax}")
    public ResponseEntity<ResponseObject> searchProductsByNameAscSortInProPriceZone(@PathVariable String proName, @PathVariable Integer page, @PathVariable Long proPriceMin, @PathVariable Long proPriceMax) {
        return productServices.searchProductsByNameAscSortInProPriceZone(proName, page, proPriceMin, proPriceMax);
    }

    @GetMapping("/search/query={proName}&page={page}&sort=pro.price&order=desc/min-price={proPriceMin}&max-price={proPriceMax}")
    public ResponseEntity<ResponseObject> searchProductsByNameDescSortInProPriceZone(@PathVariable String proName, @PathVariable Integer page, @PathVariable Long proPriceMin, @PathVariable Long proPriceMax) {
        return productServices.searchProductsByNameDescSortInProPriceZone(proName, page, proPriceMin, proPriceMax);
    }
}
