package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.entity.Brand;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandServices {
    private final BrandRepository brandRepository;

    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    public ResponseEntity<ResponseObject> saveNewBrand(Brand brand) {
        Brand exists = brandRepository.findBrandByName(brand.getName());
        return exists == null
                ? ResponseEntity.ok()
                .body(new ResponseObject("OK", "save new brand successfully", brandRepository.save(brand)))
                : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(new ResponseObject("FAlSE", "This brand is exists !", ""));
    }

    public ResponseEntity<ResponseObject> deleteBrandById(Long id) {
        Optional<Brand> exists = brandRepository.findById(id);
        if (exists.isPresent()) {
            brandRepository.deleteById(id);
            return ResponseEntity.ok()
                    .body(new ResponseObject("OK", "Delete successfully !", id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseObject("FALSE", "This brand is not exists !", id));
    }

    public ResponseEntity<ResponseObject> updateBrandById(Long id, Brand brand) {
        Brand brandUpdate = brandRepository.findById(id).orElseThrow(() -> new IllegalStateException("This brand is not exists !"));
        brandUpdate.setName(brand.getName());
        brandUpdate.setUpdatedAt(brand.getUpdatedAt());
        brandRepository.save(brandUpdate);
        return ResponseEntity.ok().body(new ResponseObject("OK", "update successfully", brandUpdate));
    }
}
