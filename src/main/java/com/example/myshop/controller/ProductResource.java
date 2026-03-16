package com.example.myshop.controller;

import com.example.myshop.dto.ProductDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.ProductPayload;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/products")
public interface ProductResource {
    @PostMapping
    ResponseEntity<ProductDTO> create(@Valid @ModelAttribute ProductPayload productPayload) throws I18nException, IOException;

}
