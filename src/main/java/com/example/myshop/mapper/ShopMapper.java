package com.example.myshop.mapper;

import com.example.myshop.dto.ShopDTO;
import com.example.myshop.entity.Shop;
import org.springframework.stereotype.Component;

@Component
public class ShopMapper extends AbstractMapper<Shop, ShopDTO> {
    @Override
    public Class<ShopDTO> getDtoClass() {
        return ShopDTO.class;
    }

    @Override
    public Class<Shop> getEntityClass() {
        return Shop.class;
    }
}
