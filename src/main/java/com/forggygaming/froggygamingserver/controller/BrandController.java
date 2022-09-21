package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.Brand;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.service.BrandServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/brand")
@CrossOrigin("http://localhost:3000")

@RequiredArgsConstructor
public class BrandController {
    private final BrandServices brandServices;

    @GetMapping

    public List<Brand> getBrands() {
        return brandServices.getBrands();
    }

    @PostMapping(value = "/save",  consumes = {"application/json"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> saveNewBrand(@RequestBody Brand brand) {
        return brandServices.saveNewBrand(brand);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public ResponseEntity<ResponseObject> updateBrandById(@PathVariable Long id, @RequestBody Brand brand) {
        return brandServices.updateBrandById(id, brand);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> deleteBrandById(@PathVariable Long id) {
        return brandServices.deleteBrandById(id);
    }

    @GetMapping("/search/query={brandName}")
    public ResponseEntity<ResponseObject> searchBrandByBrandName(@PathVariable String brandName) {
        return brandServices.searchBrandByBrandName(brandName);
    }

    @PostMapping("/{brandId}/remove/pro-id={proId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> deleteProductByProId(@PathVariable Long brandId, @PathVariable Long proId) {
        return brandServices.deleteProductByProId(brandId, proId);
    }
}
