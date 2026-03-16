package com.example.myshop.mapper;

import com.example.myshop.dto.ProductDTO;
import com.example.myshop.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends AbstractMapper<Product, ProductDTO> {
    @Override
    public Class<ProductDTO> getDtoClass() {
        return ProductDTO.class;
    }

    @Override
    public Class<Product> getEntityClass() {
        return Product.class;
    }
}
