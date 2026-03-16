package com.example.myshop.repository;

import com.example.myshop.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product, String> {
}
