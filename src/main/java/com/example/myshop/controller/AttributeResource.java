package com.example.myshop.controller;

import com.example.myshop.dto.AttributeDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.AttributePayload;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/attributes")
public interface AttributeResource {
    @PostMapping
    ResponseEntity<AttributeDTO> create(@Valid @RequestBody AttributePayload attributePayload);

    @PutMapping("/{id}")
    ResponseEntity<AttributeDTO> update(@PathVariable String id, @Valid @RequestBody AttributePayload attributePayload) throws I18nException;

    @GetMapping
    ResponseEntity<List<AttributeDTO>> search(@RequestParam(required = true) String key) throws I18nException;
}
