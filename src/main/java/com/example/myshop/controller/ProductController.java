package com.example.myshop.controller;

import com.example.myshop.dto.ProductDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.ProductPayload;
import com.example.myshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ProductController implements ProductResource {
    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<ProductDTO> create(ProductPayload productPayload) throws I18nException, IOException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.create(productPayload));
    }
}
