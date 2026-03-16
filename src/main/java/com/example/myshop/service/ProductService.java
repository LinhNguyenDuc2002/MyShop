package com.example.myshop.service;

import com.example.myshop.dto.ProductDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.ProductPayload;

import java.io.IOException;

public interface ProductService {
    ProductDTO create(ProductPayload productPayload) throws I18nException, IOException;
}
