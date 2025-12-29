package com.example.myshop.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.example.myshop.constant.CloudinaryConstant;
import com.example.myshop.dto.CategoryDTO;
import com.example.myshop.entity.Category;
import com.example.myshop.entity.Image;
import com.example.myshop.exception.I18nException;
import com.example.myshop.mapper.CategoryMapper;
import com.example.myshop.payload.CategoryPayload;
import com.example.myshop.repository.CategoryRepository;
import com.example.myshop.service.CategoryService;
import com.example.myshop.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final String FOLDER = "category";

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public CategoryDTO create(CategoryPayload categoryPayload) throws I18nException, IOException {
        Category category = Category.builder()
                .name(categoryPayload.getName())
                .description(categoryPayload.getDescription())
                .image(new Image())
                .build();

        categoryRepository.save(category);

        Map<String, String> args = ObjectUtils.asMap();
        args.put(CloudinaryConstant.FOLDER, FOLDER);
        args.put(CloudinaryConstant.PUBLIC_ID, category.getImage().getId());
//        cloudinaryService.upload(categoryPayload.getImage(), args);

        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDTO update(String id, CategoryPayload categoryPayload) {
        return null;
    }

    @Override
    public List<CategoryDTO> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toDtoList(categories);
    }

    @Override
    public CategoryDTO delete(String id) throws I18nException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> I18nException.builder()
                        .code(HttpStatus.NOT_FOUND)
                        .message("")
                        .build()
                );

        categoryRepository.deleteById(id);
        return categoryMapper.toDto(category);
    }
}
