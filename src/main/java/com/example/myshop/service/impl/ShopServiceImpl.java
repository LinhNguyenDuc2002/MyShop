package com.example.myshop.service.impl;

import com.example.myshop.dto.ShopDTO;
import com.example.myshop.entity.Address;
import com.example.myshop.entity.Shop;
import com.example.myshop.exception.I18nException;
import com.example.myshop.mapper.ShopMapper;
import com.example.myshop.payload.ShopPayload;
import com.example.myshop.repository.ShopRepository;
import com.example.myshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public ShopDTO update(String id, ShopPayload shopPayload) throws I18nException {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> {
                    return I18nException.builder()
                            .code(HttpStatus.NOT_FOUND)
                            .message("")
                            .build();
                });

        shop.setName(shopPayload.getName());
        shop.setDescription(shopPayload.getDescription());

//        if(shopPayload.getAddress() != null) {
//            Address address = shop.getAddress();
//            if(address == null) address = new Address();
//
//            address.setCity();
//        }

        shopRepository.save(shop);
        return shopMapper.toDto(shop);
    }
}
