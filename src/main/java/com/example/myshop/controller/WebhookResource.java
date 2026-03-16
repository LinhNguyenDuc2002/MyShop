package com.example.myshop.controller;

import com.example.myshop.exception.I18nException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/webhook")
public interface WebhookResource {
    @PostMapping("/cloudinary")
    ResponseEntity<?> cloudinaryWebhook(@RequestBody Map<String, Object> payload) throws I18nException;
}
