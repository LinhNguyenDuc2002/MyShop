package com.example.myshop.service;

import com.example.myshop.dto.ShopDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.ShopPayload;

public interface ShopService {
    ShopDTO update(String id, ShopPayload shopPayload) throws I18nException;
}
