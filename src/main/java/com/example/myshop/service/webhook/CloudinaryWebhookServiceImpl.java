package com.example.myshop.service.webhook;

import com.example.myshop.constant.CloudinaryConstant;
import com.example.myshop.entity.Image;
import com.example.myshop.exception.I18nException;
import com.example.myshop.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CloudinaryWebhookServiceImpl implements CloudinaryWebhookService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void update(Map<String, Object> payload) throws I18nException {
        String publicId = (String) payload.get(CloudinaryConstant.PUBLIC_ID);
        Image image = imageRepository.findByPublicId(publicId);

        if (image == null) {
            throw I18nException.builder()
                    .code(HttpStatus.NOT_FOUND)
                    .build();
        }

        image.setSecureUrl((String) payload.get(CloudinaryConstant.SECURE_URL));
        image.setResourceType((String) payload.get(CloudinaryConstant.RESOURCE_TYPE));
        image.setFormat((String) payload.get(CloudinaryConstant.FORMAT));
        imageRepository.save(image);
    }
}
