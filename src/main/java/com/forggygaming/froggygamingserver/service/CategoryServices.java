package com.forggygaming.froggygamingserver.service;

import com.forggygaming.froggygamingserver.entity.Category;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServices {
    private final CategoryRepo categoryRepo;

    public ResponseEntity<ResponseObject> getCategories(Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 12);
        Page<Category> categories = categoryRepo.findAll(pageable);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", categories));
    }

    public ResponseEntity<ResponseObject> saveNewCategory(Category category) {
        Category exists = categoryRepo.findCategoryByCateName(category.getCateName());
        if (exists != null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("FALSE", "This category is exists !", categoryRepo.findCategoryByCateName(category.getCateName())));
        }
        category.setCreatedAt(LocalDate.now());
        category.setUpdatedAt(LocalDate.now());
        categoryRepo.save(category);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", category));
    }

    public ResponseEntity<ResponseObject> deleteCategoryById(Long id) {
        Optional<Category> exists = categoryRepo.findById(id);
        if (exists.isPresent()) {
            categoryRepo.deleteById(id);
            return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", id));
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("FALSE", "This category is not exists !", id));
    }

    public ResponseEntity<ResponseObject> updateCategoryById(Long id, Category category) {
        Category categoryUpdate = categoryRepo.findById(id).orElseThrow(() -> new IllegalStateException("This category is not exists !"));
        categoryUpdate.setCateName(category.getCateName());
        categoryUpdate.setUpdatedAt(LocalDate.now());
        categoryRepo.save(categoryUpdate);
        return ResponseEntity.ok().body(new ResponseObject("OK", "Successfully", categoryUpdate));
    }
}
