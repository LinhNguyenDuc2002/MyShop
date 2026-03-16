package com.example.myshop.service;

import com.example.myshop.dto.CategoryDTO;
import com.example.myshop.dto.PaginationDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.CategoryPayload;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    CategoryDTO create(CategoryPayload categoryPayload) throws I18nException, IOException;

    CategoryDTO update(String id, CategoryPayload categoryPayload) throws I18nException, IOException;

    PaginationDTO<CategoryDTO> getAll(Integer page, Integer size, String search, List<String> sortColumns);

    List<CategoryDTO> getAll(String search);

    CategoryDTO delete(String id) throws I18nException;
}
