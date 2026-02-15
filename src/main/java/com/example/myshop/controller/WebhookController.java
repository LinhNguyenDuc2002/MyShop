package com.example.myshop.controller;

import com.example.myshop.exception.I18nException;
import com.example.myshop.service.webhook.CloudinaryWebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WebhookController implements WebhookResource {
    @Autowired
    private CloudinaryWebhookService cloudinaryWebhookService;

    @Override
    public ResponseEntity<?> cloudinaryWebhook(Map<String, Object> payload) throws I18nException {
        cloudinaryWebhookService.update(payload);

        return ResponseEntity.noContent().build();
    }
}
