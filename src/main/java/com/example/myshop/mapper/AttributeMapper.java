package com.example.myshop.mapper;

import com.example.myshop.dto.AttributeDTO;
import com.example.myshop.entity.Attribute;
import org.springframework.stereotype.Component;

@Component
public class AttributeMapper extends AbstractMapper<Attribute, AttributeDTO> {
    @Override
    public Class<AttributeDTO> getDtoClass() {
        return AttributeDTO.class;
    }

    @Override
    public Class<Attribute> getEntityClass() {
        return Attribute.class;
    }
}
