package com.example.myshop.mapper;

import com.example.myshop.dto.CategoryDTO;
import com.example.myshop.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper extends AbstractMapper<Category, CategoryDTO> {
    @Override
    public Class<CategoryDTO> getDtoClass() {
        return CategoryDTO.class;
    }

    @Override
    public Class<Category> getEntityClass() {
        return Category.class;
    }
}
