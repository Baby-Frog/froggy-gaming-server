package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.entity.*;
import com.forggygaming.froggygamingserver.form.AddProductToBrandForm;
import com.forggygaming.froggygamingserver.form.AddProductToCategoryForm;
import com.forggygaming.froggygamingserver.repository.BrandRepo;
import com.forggygaming.froggygamingserver.repository.CategoryRepo;
import com.forggygaming.froggygamingserver.repository.ProductDetailRepo;
import com.forggygaming.froggygamingserver.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServices {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final BrandRepo brandRepo;

    private final ProductDetailRepo productDetailRepo;
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public ResponseEntity<ResponseObject> saveNewProduct(Product product) {
        Product exists = productRepo.findProductByProName(product.getProName());
        if(exists == null) {
            product.setCreatedAt(LocalDate.now());
            product.setUpdatedAt(LocalDate.now());
            productRepo.save(product);
            return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", product));
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("FALSE", "This product is exists !", exists));
    }

    public ResponseEntity<ResponseObject> deleteProductById(Long id) {
        Optional<Product> exists = productRepo.findById(id);
        if (exists.isPresent()) {
            productRepo.deleteById(id);
            return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", id));
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("FALSE", "This product is not exists !", id));
    }

    public ResponseEntity<ResponseObject> updateProductById(Long id, Product product) {
        Product productUpdate = productRepo.findProductByProId(id);

        if(productUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "This product is not exists !", id));
        }

        productUpdate.setProName(product.getProName());
        productUpdate.setProDesc(product.getProDesc());
        productUpdate.setProPrice(product.getProPrice());
        productUpdate.setUpdatedAt(LocalDate.now());
        productRepo.save(productUpdate);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", productUpdate));
    }

    public ResponseEntity<ResponseObject> addToCategory(AddProductToCategoryForm form) {
        Category category = categoryRepo.findCategoryByCateName(form.getCateName());
        Product product = productRepo.findProductByProName(form.getProName());
        if (category == null || product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "Not exists", null));
        }
        category.addProduct(product);
        category.setUpdatedAt(LocalDate.now());
        categoryRepo.save(category);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", category));
    }

    public ResponseEntity<ResponseObject> addToBrand(AddProductToBrandForm form) {
        Brand brand = brandRepo.findBrandByBrandName(form.getBrandName());
        Product product = productRepo.findProductByProName(form.getProName());
        if(brand == null || product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "Not exists", null));
        }
        brand.addProduct(product);
        brand.setUpdatedAt(LocalDate.now());
        brandRepo.save(brand);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", brand));
    }


    public ResponseEntity<ResponseObject> removeProductDetail(Long id, Long productDetailId) {
        Product product = productRepo.findProductByProId(id);
        ProductDetail productDetail = productDetailRepo.findProductDetailById(productDetailId);

        if(productDetail == null || product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "Not exists", null));
        }

        product.remoteProductDetail(productDetail);
        product.setUpdatedAt(LocalDate.now());
        productRepo.save(product);

        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", product));
    }
}
