package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.Image;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.form.AddImageToProductForm;
import com.forggygaming.froggygamingserver.service.ImageServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/image")
@CrossOrigin("http://localhost:3000")

@RequiredArgsConstructor
public class ImageController {
    private final ImageServices imageServices;

    @GetMapping

    public List<Image> getImages() {
        return imageServices.getImages();
    }

    @PostMapping(value = "/save", consumes = {"application/json"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> saveNewCategory(@RequestBody Image image) {
        return imageServices.saveNewImage(image);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> deleteImageById(@PathVariable Long id) {
        return imageServices.deleteImageById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> updateCategoryById(@PathVariable Long id, @RequestBody Image image) {
        return imageServices.updateImageById(id, image);
    }

    @PostMapping("/add-to-product")

    public ResponseEntity<ResponseObject> addtocategory(@RequestBody AddImageToProductForm form) {
        return imageServices.addtoproduct(form);
    }
}
