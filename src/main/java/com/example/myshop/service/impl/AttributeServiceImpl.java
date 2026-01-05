package com.example.myshop.service.impl;

import com.example.myshop.dto.AttributeDTO;
import com.example.myshop.entity.Attribute;
import com.example.myshop.exception.I18nException;
import com.example.myshop.mapper.AttributeMapper;
import com.example.myshop.payload.AttributePayload;
import com.example.myshop.repository.AttributeRepository;
import com.example.myshop.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private AttributeMapper attributeMapper;

    @Override
    public AttributeDTO create(AttributePayload attributePayload) {
        Attribute attribute = attributeRepository.save(
                Attribute.builder()
                        .type(attributePayload.getType())
                        .description(attributePayload.getDescription())
                        .build()
        );
        return attributeMapper.toDto(attribute);
    }

    @Override
    public AttributeDTO update(String id, AttributePayload attributePayload) throws I18nException {
        Attribute attribute = attributeRepository.findById(id)
                .orElseThrow(() -> {
                    return I18nException.builder()
                            .code(HttpStatus.NOT_FOUND)
                            .message("")
                            .build();
                });

        attribute.setType(attributePayload.getType());
        attribute.setDescription(attributePayload.getDescription());
        attributeRepository.save(attribute);
        return attributeMapper.toDto(attribute);
    }

    @Override
    public List<AttributeDTO> search(String key) {
        return List.of();
    }
}
