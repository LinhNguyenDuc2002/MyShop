package com.example.myshop.controller;

import com.example.myshop.dto.CategoryDTO;
import com.example.myshop.dto.PaginationDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.CategoryPayload;
import com.example.myshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CategoryController implements CategoryResource {
    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseEntity<CategoryDTO> create(CategoryPayload categoryPayload) throws I18nException, IOException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.create(categoryPayload));
    }

    @Override
    public ResponseEntity<CategoryDTO> update(String id, CategoryPayload categoryPayload) throws I18nException, IOException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.update(id, categoryPayload));
    }

    @Override
    public ResponseEntity<PaginationDTO<CategoryDTO>> getAll(Integer page, Integer size, String search, List<String> sortColumns) {
        return ResponseEntity.ok(categoryService.getAll(page, size, search, sortColumns));
    }

    @Override
    public ResponseEntity<List<CategoryDTO>> getAll(String search) {
        return ResponseEntity.ok(categoryService.getAll(search));
    }

    @Override
    public ResponseEntity<CategoryDTO> delete(String id) throws I18nException {
        return ResponseEntity.ok(categoryService.delete(id));
    }
}
