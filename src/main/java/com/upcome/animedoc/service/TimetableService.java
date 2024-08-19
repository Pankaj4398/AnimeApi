package com.upcome.animedoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TimetableService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.base.url}")
    private String BASE_URL;

    public Object getTimetable(int year, int week, String timezone) {
        String url = String.format("%s?year=%d&week=%d&tz=%s", BASE_URL, year, week, timezone);

        try {
            return restTemplate.getForObject(url, Object.class);
        } catch (Exception e) {
            // Handle exceptions
            throw new RuntimeException("Failed to fetch timetable data", e);
        }
    }

    public Object getTimetableByAirType(String airType, int year, int week, String timezone) {
        String url = String.format("%s/%s?year=%d&week=%d&tz=%s", BASE_URL, airType, year, week, timezone);

        try {
            return restTemplate.getForObject(url, Object.class);
        } catch (Exception e) {
            // Handle exceptions
            throw new RuntimeException("Failed to fetch timetable data", e);
        }
    }
}
