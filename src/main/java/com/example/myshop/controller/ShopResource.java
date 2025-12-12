package com.example.myshop.controller;

import com.example.myshop.dto.ShopDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.ShopPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/shop")
public interface ShopResource {
    @PutMapping("/update/{id}")
    ResponseEntity<ShopDTO> update(@PathVariable String id, @RequestBody ShopPayload shopPayload) throws I18nException;
}
