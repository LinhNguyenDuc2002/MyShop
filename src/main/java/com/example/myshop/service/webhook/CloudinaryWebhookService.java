package com.example.myshop.service.webhook;

import com.example.myshop.exception.I18nException;

import java.util.Map;

public interface CloudinaryWebhookService {
    void update(Map<String, Object> payload) throws I18nException;
}
