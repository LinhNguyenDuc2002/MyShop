package com.example.myshop.controller;

import com.example.myshop.dto.CategoryDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.CategoryPayload;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/categories")
public interface CategoryResource {
    @PostMapping
    ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryPayload categoryPayload) throws I18nException, IOException;

    @PutMapping("/{id}")
    ResponseEntity<CategoryDTO> update(@PathVariable String id, @RequestBody CategoryPayload categoryPayload);

    @GetMapping
    ResponseEntity<List<CategoryDTO>> getAll();

    @DeleteMapping("/{id}")
    ResponseEntity<CategoryDTO> delete(@PathVariable String id) throws I18nException;
}
