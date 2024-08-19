package com.upcome.animedoc.controller;

import com.upcome.animedoc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{categoryType}/{slug}")
    public ResponseEntity<?> getCategoryData(
            @PathVariable String categoryType,
            @PathVariable String slug) {

        try {
            // Validate categoryType
            if (!"genres".equals(categoryType) && !"studios".equals(categoryType) &&
                    !"sources".equals(categoryType) && !"media-types".equals(categoryType)) {
                return ResponseEntity.badRequest().body("Invalid categoryType value.");
            }

            // Fetch category data from the service
            Object categoryData = categoryService.getCategoryData(categoryType, slug);
            return ResponseEntity.ok(categoryData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch category data.");
        }
    }

    @GetMapping("/{categoryType}")
    public ResponseEntity<?> getCategories(
            @PathVariable String categoryType,
            @RequestParam(value = "q", required = false) String query) {

        try {
            // Validate categoryType
            if (!"genres".equals(categoryType) && !"studios".equals(categoryType) &&
                    !"sources".equals(categoryType) && !"media-types".equals(categoryType)) {
                return ResponseEntity.badRequest().body("Invalid categoryType value.");
            }

            // Fetch multiple categories from the service
            Object categories = categoryService.getCategories(categoryType, query);
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch categories data.");
        }
    }
}
