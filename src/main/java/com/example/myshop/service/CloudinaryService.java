package com.example.myshop.service;

import com.example.myshop.exception.I18nException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CloudinaryService {
    void upload(MultipartFile file, Map<String, String> args) throws IOException, I18nException;

    void destroy(String id) throws IOException;

    void upload(Map<String, MultipartFile> files) throws IOException;

    void destroy(List<String> ids) throws IOException;
}
