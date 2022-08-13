package com.forggygaming.froggygamingserver.controller;

import com.forggygaming.froggygamingserver.entity.Category;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.service.CategoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServices categoryServices;

    @GetMapping
    public List<Category> getCategories() {
        return categoryServices.getCategories();
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseObject> saveNewCategory(@RequestBody Category category) {
        return categoryServices.saveNewCategory(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteCategoryById(@PathVariable Long id) {
        return categoryServices.deleteCategoryById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateCategoryById(@PathVariable Long id, @RequestBody Category category) {
        return categoryServices.updateCategoryById(id, category);
    }
}
