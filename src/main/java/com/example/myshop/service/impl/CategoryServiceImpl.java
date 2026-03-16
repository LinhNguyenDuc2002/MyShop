package com.example.myshop.service.impl;

import com.example.myshop.constant.CloudinaryConstant;
import com.example.myshop.constant.ImageStatus;
import com.example.myshop.dto.CategoryDTO;
import com.example.myshop.dto.PaginationDTO;
import com.example.myshop.entity.Category;
import com.example.myshop.entity.Image;
import com.example.myshop.exception.I18nException;
import com.example.myshop.mapper.CategoryMapper;
import com.example.myshop.payload.CategoryPayload;
import com.example.myshop.repository.CategoryRepository;
import com.example.myshop.repository.ImageRepository;
import com.example.myshop.repository.predicate.CategoryPredicate;
import com.example.myshop.service.CategoryService;
import com.example.myshop.service.CloudinaryService;
import com.example.myshop.util.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public CategoryDTO create(CategoryPayload categoryPayload) throws I18nException, IOException {
        if (categoryPayload.getImage() == null || categoryPayload.getImage().isEmpty()) {
            throw I18nException.builder().build();
        }

        Category category = Category.builder()
                .name(categoryPayload.getName())
                .description(categoryPayload.getDescription())
                .build();
//        categoryRepository.save(category);

//        CompletableFuture<Map> future = cloudinaryService.upload(categoryPayload.getImage(), new HashMap<String, String>());
//        future.thenAccept(result -> {
//            String publicId = (String) result.get(CloudinaryConstant.PUBLIC_ID);
//            Image image = imageRepository.save(Image.builder()
//                    .publicId(publicId)
//                    .status(ImageStatus.PENDING)
//                    .build());
//            category.setImage(image);
//            categoryRepository.save(category);
//        });

        Map<String, Object> result = cloudinaryService.upload(categoryPayload.getImage(), new HashMap<String, String>());
        Image image = imageRepository.save(Image.builder()
                .publicId((String) result.get(CloudinaryConstant.PUBLIC_ID))
                .secureUrl((String) result.get(CloudinaryConstant.SECURE_URL))
                .resourceType((String) result.get(CloudinaryConstant.RESOURCE_TYPE))
                .format((String) result.get(CloudinaryConstant.FORMAT))
                .status(ImageStatus.COMPLETED)
                .build());
        category.setImage(image);
        categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDTO update(String id, CategoryPayload categoryPayload) throws I18nException, IOException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    return I18nException.builder()
                            .code(HttpStatus.NOT_FOUND)
                            .message("")
                            .build();
                });

        category.setName(categoryPayload.getName());
        category.setDescription(categoryPayload.getDescription());
//        if (categoryPayload.getImage() != null && !categoryPayload.getImage().isEmpty()) {
//            Map<String, String> args = new HashMap<>();
//
//            if (category.getImage() != null) {
//                args.put(CloudinaryConstant.PUBLIC_ID, category.getImage().getPublicId());
//            }
//            Image image = cloudinaryService.upload(categoryPayload.getImage(), args);
//            category.setImage(image);
//        }
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public PaginationDTO<CategoryDTO> getAll(Integer page, Integer size, String search, List<String> sortColumns) {
        Pageable pageable = (sortColumns == null) ? PaginationUtils.getPage(page, size) : PaginationUtils.getPage(page, size, sortColumns.toArray(new String[0]));
        CategoryPredicate categoryPredicate = new CategoryPredicate().search(search);
        Page<Category> categories = categoryRepository.findAll(categoryPredicate.getCriteria(), pageable);

        return PaginationDTO.<CategoryDTO>builder()
                .index(categories.getNumber())
                .totalPage(categories.getTotalPages())
                .elements(categoryMapper.toDtoList(categories.getContent()))
                .build();
    }

    @Override
    public List<CategoryDTO> getAll(String search) {
        CategoryPredicate categoryPredicate = new CategoryPredicate().search(search);
        List<Category> categories = categoryRepository.findAll(categoryPredicate.getCriteria());

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
