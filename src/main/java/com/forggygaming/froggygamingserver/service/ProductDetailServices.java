package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.entity.Product;
import com.forggygaming.froggygamingserver.entity.ProductDetail;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.form.AddProductDetailToProductForm;
import com.forggygaming.froggygamingserver.repository.ProductDetailRepo;
import com.forggygaming.froggygamingserver.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDetailServices {
    private final ProductDetailRepo productDetailRepo;
    private final ProductRepo productRepo;

    public List<ProductDetail> getProductDetails() {
        return productDetailRepo.findAll();
    }

    public ResponseEntity<ResponseObject> saveNewProductDetail(ProductDetail productDetail) {
        productDetail.setCreatedAt(LocalDate.now());
        productDetail.setUpdatedAt(LocalDate.now());
        return ResponseEntity.ok().body(new ResponseObject("OK", "Save new product detail successfully", productDetailRepo.save(productDetail)));
    }

    public ResponseEntity<ResponseObject> deleteProductDetailById(Long id) {
        productDetailRepo.deleteById(id);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Delete product detail successfully", id));
    }

    public ResponseEntity<ResponseObject> updateProductDetailById(Long id, ProductDetail productDetail) {
        ProductDetail productDetailUpdate = productDetailRepo.findById(id).orElseThrow(() -> new IllegalStateException("This product detail is not exists !"));
        productDetailUpdate.setUpdatedAt(LocalDate.now());
        productDetailUpdate.setModel(productDetail.getModel());
        productDetailUpdate.setConnect(productDetail.getConnect());
        productDetailUpdate.setAge(productDetail.getAge());
        productDetailUpdate.setWeight(productDetail.getWeight());
        productDetailUpdate.setCompatible(productDetail.getCompatible());
        productDetailUpdate.setSize(productDetail.getSize());
        productDetailUpdate.setCable(productDetail.getCable());
        productDetailUpdate.setColor(productDetail.getColor());
        productDetailUpdate.setLed(productDetail.getLed());
        productDetailUpdate.setAccessories(productDetail.getAccessories());
        productDetailUpdate.setLayout(productDetail.getLayout());
        productDetailUpdate.setSpecialFeature(productDetail.getSpecialFeature());
        productDetailUpdate.setKeyboardSwitch(productDetail.getKeyboardSwitch());
        productDetailUpdate.setKeyboardKeycap(productDetail.getKeyboardKeycap());
        productDetailUpdate.setMouseDpi(productDetail.getMouseDpi());
        productDetailUpdate.setMouseSensor(productDetail.getMouseSensor());
        productDetailUpdate.setChairLifter(productDetail.getChairLifter());
        productDetailUpdate.setChairPillow(productDetail.getChairPillow());
        productDetailUpdate.setChairWheel(productDetail.getChairWheel());
        productDetailUpdate.setChairMaximum(productDetail.getChairMaximum());
        productDetailUpdate.setMicrophoneFrequency(productDetail.getMicrophoneFrequency());
        productDetailUpdate.setMicrophoneSens(productDetail.getMicrophoneSens());
        productDetailUpdate.setMicrophoneBitrate(productDetail.getMicrophoneBitrate());
        productDetailUpdate.setProduct(productDetail.getProduct());
        productDetailRepo.save(productDetailUpdate);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Update product detail successfully", productDetailUpdate));
    }

    public ResponseEntity<ResponseObject> addProductDetailToProductById(AddProductDetailToProductForm form) {
        ProductDetail productDetail = productDetailRepo.findProductDetailById(form.getProductDetailId());
        Product product = productRepo.findProductByProName(form.getProName());

        if (product == null || productDetail == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "This product or this product detail is not exists !", ""));
        }

        product.addProductDetail(productDetail);
        product.setProductDetail(productDetail);
        product.setUpdatedAt(LocalDate.now());
        productRepo.save(product);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Add product detail successfully", product));
    }

}
