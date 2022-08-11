package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.entity.Category;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServices {
    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public ResponseEntity<ResponseObject> saveNewCategory(Category category) {
        Optional<Category> exists = categoryRepository.findCategoryByName(category.getName());
        return exists.isEmpty()
                ? ResponseEntity.ok()
                .body(new ResponseObject("OK", "Save new category successfully", categoryRepository.save(category)))
                : ResponseEntity
                .status(HttpStatus.NOT_IMPLEMENTED)
                .body(new ResponseObject("FALSE", "this category is exists !", ""));
    }

    public ResponseEntity<ResponseObject> deleteCategoryById(Long id) {
        Optional<Category> exists = categoryRepository.findById(id);
        if (exists.isPresent()) {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok().body(new ResponseObject("OK", "Delete successfully", id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FALSE", "This category is not exists !!", id));
    }

    public ResponseEntity<ResponseObject> updateCategoryById(Long id, Category category) {
        Category categoryUpdate = categoryRepository.findById(id).orElseThrow(() -> new IllegalAccessError("this category is not exists !!"));
        categoryUpdate.setName(category.getName());
        categoryUpdate.setUpdatedAt(category.getUpdatedAt());
        return ResponseEntity.ok().body(new ResponseObject("OK", "Update category successfully", categoryUpdate));
    }
}
