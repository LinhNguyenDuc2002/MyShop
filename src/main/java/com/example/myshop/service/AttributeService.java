package com.example.myshop.service;

import com.example.myshop.dto.AttributeDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.AttributePayload;

import java.util.List;

public interface AttributeService {
    AttributeDTO create(AttributePayload attributePayload);

    AttributeDTO update(String id, AttributePayload attributePayload) throws I18nException;

    List<AttributeDTO> search(String key);

    void delete(String id) throws I18nException;

    Object delete(List<String> ids);
}
