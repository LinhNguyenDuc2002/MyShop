package com.example.myshop.converter;

import com.example.myshop.constant.ImageStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * Convert RoleType object and String
 * when saving in DB, convert RoleType to String
 * when mapping to Role entity, convert String to RoleType
 */
@Converter
@Slf4j
public class ImageStatusConverter implements AttributeConverter<ImageStatus, String> {
    @Override
    public String convertToDatabaseColumn(ImageStatus imageStatus) {
        return imageStatus == null ? ImageStatus.PENDING.name() : imageStatus.name();
    }

    @Override
    public ImageStatus convertToEntityAttribute(String s) {
        if (!StringUtils.hasText(s)) {
            return ImageStatus.PENDING;
        }
        try {
            return ImageStatus.valueOf(s);
        } catch (Exception e) {
            log.warn("Invalid role type: {}", s);
            return ImageStatus.PENDING;
        }
    }
}
