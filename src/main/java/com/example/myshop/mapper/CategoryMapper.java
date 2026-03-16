package com.example.myshop.mapper;

import com.example.myshop.constant.ImageStatus;
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

    @Override
    public CategoryDTO toDto(Category category) {
        CategoryDTO categoryDTO = super.toDto(category);

        if(category.getImage() != null && category.getImage().getStatus().equals(ImageStatus.COMPLETED)) {
            categoryDTO.setImageUrl(category.getImage().getSecureUrl());
        }

        return categoryDTO;
    }
}
