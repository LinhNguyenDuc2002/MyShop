package com.example.myshop.repository;

import com.example.myshop.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, String> {
}
