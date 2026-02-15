package com.example.myshop.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.myshop.constant.CloudinaryConstant;
import com.example.myshop.exception.I18nException;
import com.example.myshop.repository.ImageRepository;
import com.example.myshop.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ImageRepository imageRepository;

    private static final String MY_FOLDER = "my-shop";

    private static final String WEBHOOK = "";

    public CompletableFuture<Map> uploadAsync(MultipartFile file, Map<String, String> args) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return cloudinary.uploader().upload(
                        file.getBytes(),
                        ObjectUtils.asMap(
                                CloudinaryConstant.ASYNC,
                                true,
                                CloudinaryConstant.NOTIFICATION_URL,
                                WEBHOOK)
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Map<String, Object> upload(MultipartFile file, Map<String, String> args) throws IOException, I18nException {
        try {
            return cloudinary.uploader().upload(file.getBytes(), args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
