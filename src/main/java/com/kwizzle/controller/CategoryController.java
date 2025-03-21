package com.kwizzle.controller;

import com.kwizzle.model.Category;
import com.kwizzle.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Kategori tidak ditemukan");
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getCategoryByName(@PathVariable String name) {
        Category category = categoryService.getCategoryByName(name);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Kategori tidak ditemukan");
        }
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        try {
            Category saved = categoryService.saveCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Kategori berhasil dibuat dengan ID: " + saved.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        try {
            Optional<Category> updated = categoryService.updateCategory(id, category);
            if (updated.isPresent()) {
                return ResponseEntity.ok("Kategori berhasil diperbarui");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Kategori tidak ditemukan");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        boolean deleted = categoryService.deleteCategory(id);
        if (deleted) {
            return ResponseEntity.ok("Kategori berhasil dihapus");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Kategori tidak ditemukan");
        }
    }
}