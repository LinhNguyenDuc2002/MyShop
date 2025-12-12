package com.example.myshop.controller;

import com.example.myshop.dto.ShopDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.ShopPayload;
import com.example.myshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController implements ShopResource {
    @Autowired
    private ShopService shopService;

    @Override
    public ResponseEntity<ShopDTO> update(String id, ShopPayload shopPayload) throws I18nException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(shopService.update(id, shopPayload));
    }
}
