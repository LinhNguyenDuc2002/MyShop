package com.example.myshop.controller;

import com.example.myshop.dto.AttributeDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.AttributePayload;
import com.example.myshop.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AttributeController implements AttributeResource {
    @Autowired
    private AttributeService attributeService;

    @Override
    public ResponseEntity<AttributeDTO> create(AttributePayload attributePayload) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(attributeService.create(attributePayload));
    }

    @Override
    public ResponseEntity<AttributeDTO> update(String id, AttributePayload attributePayload) throws I18nException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(attributeService.update(id, attributePayload));
    }

    @Override
    public ResponseEntity<List<AttributeDTO>> search(String key) throws I18nException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(attributeService.search(key));
    }
}
