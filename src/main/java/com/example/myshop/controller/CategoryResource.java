package com.example.myshop.controller;

import com.example.myshop.constant.ParameterConstant;
import com.example.myshop.dto.CategoryDTO;
import com.example.myshop.dto.PaginationDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.CategoryPayload;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@RequestMapping("/categories")
public interface CategoryResource {
    @PostMapping
    ResponseEntity<CategoryDTO> create(@Valid @ModelAttribute CategoryPayload categoryPayload) throws I18nException, IOException;

    @PutMapping("/{id}")
    ResponseEntity<CategoryDTO> update(@PathVariable String id, @RequestBody CategoryPayload categoryPayload) throws I18nException, IOException;

    @GetMapping
    ResponseEntity<PaginationDTO<CategoryDTO>> getAll(
            @RequestParam(name = ParameterConstant.Page.PAGE, defaultValue = ParameterConstant.Page.DEFAULT_PAGE) Integer page,
            @RequestParam(name = ParameterConstant.Page.SIZE, defaultValue = ParameterConstant.Page.DEFAULT_SIZE) Integer size,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "sort-columns", required = false) List<String> sortColumns);

    @GetMapping("/search")
    ResponseEntity<List<CategoryDTO>> getAll(@RequestParam(name = "search", required = false) String search);

    @DeleteMapping("/{id}")
    ResponseEntity<CategoryDTO> delete(@PathVariable String id) throws I18nException;
}
