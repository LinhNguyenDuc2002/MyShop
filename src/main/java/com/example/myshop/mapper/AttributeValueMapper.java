package com.example.myshop.mapper;

import com.example.myshop.dto.AttributeValueDTO;
import com.example.myshop.entity.AttributeValue;
import org.springframework.stereotype.Component;

@Component
public class AttributeValueMapper extends AbstractMapper<AttributeValue, AttributeValueDTO> {
    @Override
    public Class<AttributeValueDTO> getDtoClass() {
        return AttributeValueDTO.class;
    }

    @Override
    public Class<AttributeValue> getEntityClass() {
        return AttributeValue.class;
    }
}
