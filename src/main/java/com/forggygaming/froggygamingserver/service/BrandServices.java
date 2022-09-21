package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.entity.Brand;
import com.forggygaming.froggygamingserver.entity.Product;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.repository.BrandRepo;
import com.forggygaming.froggygamingserver.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandServices {
    private final BrandRepo brandRepo;

    private final ProductRepo productRepo;

    public List<Brand> getBrands() {
        return brandRepo.findAll();
    }

    public ResponseEntity<ResponseObject> saveNewBrand(Brand brand) {
        Brand exists = brandRepo.findBrandByBrandName(brand.getBrandName());
        if (exists != null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("FALSE", "This brand is exists!", exists));
        }
        brand.setCreatedAt(LocalDate.now());
        brand.setUpdatedAt(LocalDate.now());
        brandRepo.save(brand);
        return ResponseEntity.ok().body(new ResponseObject("OK", "successfully", brand));
    }

    public ResponseEntity<ResponseObject> updateBrandById(Long id, Brand brand) {
        Brand updateBrand = brandRepo.findById(id).orElseThrow(() -> new IllegalStateException("This brand is not exists !"));
        updateBrand.setBrandName(brand.getBrandName());
        updateBrand.setUpdatedAt(LocalDate.now());
        brandRepo.save(updateBrand);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", updateBrand));
    }

    public ResponseEntity<ResponseObject> deleteBrandById(Long id) {
        Optional<Brand> exists = brandRepo.findById(id);
        if (exists.isPresent()) {
            brandRepo.deleteById(id);
            return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "This brand is not exists !", id));
    }

    public ResponseEntity<ResponseObject> searchBrandByBrandName(String brandName) {
        List<Brand> brands = brandRepo.searchBrandsByBrandName(brandName);
        if (brands.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "This brand is not exists !", null));
        }
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", brands));
    }

    public ResponseEntity<ResponseObject> deleteProductByProId(Long brandId, Long proId) {
        Brand brand = brandRepo.findBrandById(brandId);
        Product product = productRepo.findProductByProId(proId);
        brand.removeProduct(product);
        brandRepo.save(brand);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", brand));
    }
}
