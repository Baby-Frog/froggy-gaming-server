package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.Image;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.form.AddImageToProductForm;
import com.forggygaming.froggygamingserver.service.ImageServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageServices imageServices;

    @GetMapping
    public List<Image> getImages() {
        return imageServices.getImages();
    }

    @PostMapping(value = "/save", consumes = {"application/json"})
    public ResponseEntity<ResponseObject> saveNewCategory(@RequestBody Image image) {
        return imageServices.saveNewImage(image);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteImageById(@PathVariable Long id) {
        return imageServices.deleteImageById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateCategoryById(@PathVariable Long id, @RequestBody Image image) {
        return imageServices.updateImageById(id, image);
    }

    @PostMapping("/addtoproduct")
    public ResponseEntity<ResponseObject> addtocategory(@RequestBody AddImageToProductForm form) {
        return imageServices.addtoproduct(form);
    }
}
