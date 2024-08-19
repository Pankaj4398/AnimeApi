package com.upcome.animedoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CategoryService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.base.url}")
    private String BASE_URL;

    public Object getCategoryData(String categoryType, String slug) {
        String url = String.format("%s/%s/%s", BASE_URL, categoryType, slug);

        try {
            return restTemplate.getForObject(url, Object.class);
        } catch (Exception e) {
            // Handle exceptions
            throw new RuntimeException("Failed to fetch category data", e);
        }
    }

    public Object getCategories(String categoryType, String query) {
        String url = query != null
                ? String.format("%s/%s?q=%s", BASE_URL, categoryType, query)
                : String.format("%s/%s", BASE_URL, categoryType);

        try {
            return restTemplate.getForObject(url, Object.class);
        } catch (Exception e) {
            // Handle exceptions
            throw new RuntimeException("Failed to fetch categories data", e);
        }
    }
}
