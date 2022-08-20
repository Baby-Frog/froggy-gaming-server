package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.Brand;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.service.BrandServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandServices brandServices;

    @GetMapping
    public List<Brand> getBrands() {
        return brandServices.getBrands();
    }

    @PostMapping(value = "/save",  consumes = {"application/json"})
    public ResponseEntity<ResponseObject> saveNewBrand(@RequestBody Brand brand) {
        return brandServices.saveNewBrand(brand);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateBrandById(@PathVariable Long id, @RequestBody Brand brand) {
        return brandServices.updateBrandById(id, brand);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteBrandById(@PathVariable Long id) {
        return brandServices.deleteBrandById(id);
    }

}
