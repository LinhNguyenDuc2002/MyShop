package com.example.myshop.service.impl;

import com.cloudinary.Cloudinary;
import com.example.myshop.constant.CloudinaryConstant;
import com.example.myshop.entity.Image;
import com.example.myshop.exception.I18nException;
import com.example.myshop.repository.ImageRepository;
import com.example.myshop.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void upload(MultipartFile file, Map<String, String> args) throws IOException, I18nException {
        Map<String, String> result = cloudinary.uploader().upload(file.getBytes(), args);
        Image image = imageRepository.findById(args.get(CloudinaryConstant.PUBLIC_ID))
                .orElseThrow(() -> {
                    return I18nException.builder()
                            .code(HttpStatus.NOT_FOUND)
                            .message("")
                            .build();
                });

        image.setPublicId(result.get(CloudinaryConstant.PUBLIC_ID));
        image.setFormat(result.get(CloudinaryConstant.FORMAT));
        image.setResourceType(result.get(CloudinaryConstant.RESOURCE_TYPE));
        image.setSecureUrl(result.get(CloudinaryConstant.SECURE_URL));
        imageRepository.save(image);
    }

    @Override
    public void destroy(String id) throws IOException {

    }

    @Override
    public void upload(Map<String, MultipartFile> files) throws IOException {

    }

    @Override
    public void destroy(List<String> ids) throws IOException {

    }
}
