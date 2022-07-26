package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.entity.*;
import com.forggygaming.froggygamingserver.form.AddProductToBrandForm;
import com.forggygaming.froggygamingserver.form.AddProductToCategoryForm;
import com.forggygaming.froggygamingserver.form.AddProductToOrderDetailForm;
import com.forggygaming.froggygamingserver.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServices {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final BrandRepo brandRepo;
    private final OrderDetailRepo orderDetailRepo;

    private final ImageRepo imageRepo;

    public ResponseEntity<ResponseObject> getProducts(int page) {
        Pageable pageable = PageRequest.of(page - 1, 12);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", productRepo.findAll(pageable)));
    }

    public ResponseEntity<ResponseObject> saveNewProduct(Product product) {
        Product exists = productRepo.findProductByProName(product.getProName());
        if (exists == null) {
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

        if (productUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "This product is not exists !", id));
        }

        productUpdate.setProName(product.getProName());
        productUpdate.setProDesc(product.getProDesc());
        productUpdate.setProPrice(product.getProPrice());
        productUpdate.setProductDetail(product.getProductDetail());
        productUpdate.setCategory(product.getCategory());
        productUpdate.setBrand(product.getBrand());
        productUpdate.setImages(product.getImages());
        productUpdate.setProStatus(product.getProStatus());
        productUpdate.setUpdatedAt(LocalDate.now());
        productRepo.save(productUpdate);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", product));
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
        if (brand == null || product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "Not exists", null));
        }
        brand.addProduct(product);
        brand.setUpdatedAt(LocalDate.now());
        brandRepo.save(brand);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", brand));
    }

    public ResponseEntity<ResponseObject> addToOrderDetail(AddProductToOrderDetailForm form) {
        Product product = productRepo.findProductByProName(form.getProName());
        OrderDetail orderDetail = orderDetailRepo.findOrderDetailById(form.getOrderDetailId());
        if (product == null || orderDetail == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "Not exists !", null));
        }
        product.addOrderDetail(orderDetail);
        productRepo.save(product);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", orderDetail));
    }

    public ResponseEntity<ResponseObject> searchProductsByProName(String proName, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 12);
        Page<Product> products = productRepo.findProductsByProName(proName, pageable);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> ascSortProductByProName() {
        List<Product> products = productRepo.ascSortProductsByProName();
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> descSortProductByProName() {
        List<Product> products = productRepo.descSortProductsByProName();
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> ascSortProductByProPrice() {
        List<Product> products = productRepo.ascSortProductsByProPrice();
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> descSortProductByProPrice() {
        List<Product> products = productRepo.descSortProductsByProPrice();
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> ascSortProductsByCreatedAt() {
        List<Product> products = productRepo.ascSortProductsByCreatedAt();
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> descSortProductsByCreatedAt() {
        List<Product> products = productRepo.descSortProductsByCreatedAt();
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> getProductsInProPriceZone(Double proPriceMin, Double proPriceMax) {
        List<Product> products = productRepo.findProductsInProPriceZone(proPriceMin, proPriceMax);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByNameAndAscSortByPrice(String proName) {
        List<Product> products = productRepo.getProductsListAndAscSortByProPrice(proName);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByNameAndDescSortByPrice(String proName) {
        List<Product> products = productRepo.getProductsListAndDescSortByProPrice(proName);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByNameAndAscSortByDate(String proName) {
        List<Product> products = productRepo.getProductsListAndAscSortByProDate(proName);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByNameAndDescSortByDate(String proName) {
        List<Product> products = productRepo.getProductsListAndDescSortByProDate(proName);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByNameAndAscSortByName(String proName) {
        List<Product> products = productRepo.getProductsListAndAscSortByProName(proName);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByNameAndDescSortByName(String proName) {
        List<Product> products = productRepo.getProductsListAndDescSortByProName(proName);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> getProduct(Long id) {
        Product exists = productRepo.findProductByProId(id);
        if (exists == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "Not exists !", new ArrayList<>()));
        }
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", exists));
    }

    public ResponseEntity<ResponseObject> getProductsByCateId(Long cateId) {
        Category category = categoryRepo.findCategoryByCateId(cateId);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "Not exists !", new ArrayList<>()));
        }
        List<Product> products = productRepo.productListByCateId(cateId);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> getProductsByBrandId(Long brandId) {
        Brand brand = brandRepo.findBrandById(brandId);
        if (brand == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "Not exists !", new ArrayList<>()));
        }
        List<Product> products = productRepo.productListByBrandId(brandId);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByProNameAndAscSortByPrice(String proName, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 12, Sort.by("proPrice").ascending());
        Page<Product> products = productRepo.findProductsByProName(proName, pageable);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByProNameAndDescSortByPrice(String proName, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 12, Sort.by("proPrice").descending());
        Page<Product> products = productRepo.findProductsByProName(proName, pageable);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByProNameAndAscSortByName(String proName, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 12, Sort.by("proName").ascending());
        Page<Product> products = productRepo.findProductsByProName(proName, pageable);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByProNameAndDescSortByName(String proName, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 12, Sort.by("proName").descending());
        Page<Product> products = productRepo.findProductsByProName(proName, pageable);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByNameAndCategoryId(String proName, Integer page, Long cateId) {
        Pageable pageable = PageRequest.of(page - 1, 12);
        Page<Product> products = productRepo.findProductsByProNameAndCategory(proName, cateId, pageable);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByNameAndCategoryIdAscSortByProPrice(String proName, Integer page, Long cateId) {
        Pageable pageable = PageRequest.of(page - 1, 12, Sort.by("proPrice").ascending());
        Page<Product> products = productRepo.findProductsByProNameAndCategory(proName, cateId, pageable);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByNameAndCategoryIdDescSortByProPrice(String proName, Integer page, Long cateId) {
        Pageable pageable = PageRequest.of(page - 1, 12, Sort.by("proPrice").descending());
        Page<Product> products = productRepo.findProductsByProNameAndCategory(proName, cateId, pageable);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByNameAndCategoryIdAscSortByProName(String proName, Integer page, Long cateId) {
        Pageable pageable = PageRequest.of(page - 1, 12, Sort.by("proName").ascending());
        Page<Product> products = productRepo.findProductsByProNameAndCategory(proName, cateId, pageable);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByNameAndCategoryIdDescSortByProName(String proName, Integer page, Long cateId) {
        Pageable pageable = PageRequest.of(page - 1, 12, Sort.by("proName").descending());
        Page<Product> products = productRepo.findProductsByProNameAndCategory(proName, cateId, pageable);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByNameInProPriceZone(String proName, Integer page, Long proPriceMin, Long proPriceMax) {
        Pageable pageable = PageRequest.of(page - 1, 12);
        Page<Product> products = productRepo.productsListByProNameAndProPriceZone(proName, proPriceMin, proPriceMax, pageable);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByNameAscSortInProPriceZone(String proName, Integer page, Long proPriceMin, Long proPriceMax) {
        Pageable pageable = PageRequest.of(page - 1, 12, Sort.by("proPrice").ascending());
        Page<Product> products = productRepo.productsListByProNameAndProPriceZone(proName, proPriceMin, proPriceMax, pageable);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> searchProductsByNameDescSortInProPriceZone(String proName, Integer page, Long proPriceMin, Long proPriceMax) {
        Pageable pageable = PageRequest.of(page - 1, 12, Sort.by("proPrice").descending());
        Page<Product> products = productRepo.productsListByProNameAndProPriceZone(proName, proPriceMin, proPriceMax, pageable);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", products));
    }

    public ResponseEntity<ResponseObject> removeAllImageInProduct(Long proId) {
        Product product = productRepo.findProductByProId(proId);
        product.removeImage();
        productRepo.save(product);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", product));
    }

    public ResponseEntity<ResponseObject> removeProductDetailInProduct(Long proId) {
        Product product = productRepo.findProductByProId(proId);
        product.removeProductDetail(product.getProductDetail());
        productRepo.save(product);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", product));
    }
}
