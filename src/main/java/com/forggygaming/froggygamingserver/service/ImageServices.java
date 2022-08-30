package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.controller.FileUploadController;
import com.forggygaming.froggygamingserver.entity.Category;
import com.forggygaming.froggygamingserver.entity.Image;
import com.forggygaming.froggygamingserver.entity.Product;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.form.AddImageToProductForm;
import com.forggygaming.froggygamingserver.form.AddProductToCategoryForm;
import com.forggygaming.froggygamingserver.repository.ImageRepo;
import com.forggygaming.froggygamingserver.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageServices {
    private final ImageRepo imageRepo;
    private final ProductRepo productRepo;
    private final ImgServices imgServices;
    private final Path storageFolder= Paths.get("uploads");

    public List<Image> getImages() {
        return imageRepo.findAll();
    }

    public ResponseEntity<ResponseObject> saveNewImage(Image image) {
        Image exists = imageRepo.findImageByImgName(image.getImgName());

        if (exists != null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("FALSE", "This image is exists !", exists));
        }
        image.setCreatedAt(LocalDate.now());
        image.setUpdatedAt(LocalDate.now());
        imageRepo.save(image);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", image));
    }

    public ResponseEntity<ResponseObject> deleteImageById(Long id) {
        Optional<Image> exists = imageRepo.findById(id);
        if (exists.isPresent()) {
            imageRepo.deleteById(id);
            return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", id));
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("FALSE", "This image is not exists !", id));
    }

    public ResponseEntity<ResponseObject> updateImageById(Long id, Image image) {
        Image imageUpdate = imageRepo.findById(id).orElseThrow(() -> new IllegalStateException("This image is not exists !"));
        imageUpdate.setImgName(image.getImgName());
        imageUpdate.setImgPath(image.getImgPath());
        imageUpdate.setUpdatedAt(LocalDate.now());
        imageRepo.save(imageUpdate);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", imageUpdate));
    }

    public ResponseEntity<ResponseObject> addtoproduct(AddImageToProductForm form) {
        Image image = imageRepo.findImageByImgName(form.getImgName());
        Product product = productRepo.findProductByProName(form.getProName());
        if (image == null || product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "Not exists", null));
        }
        product.addImage(image);
        product.setUpdatedAt(LocalDate.now());
        productRepo.save(product);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", product));
    }
}
