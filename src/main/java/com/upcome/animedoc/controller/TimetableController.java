package com.upcome.animedoc.controller;

import com.upcome.animedoc.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/timetables")
public class TimetableController {
    @Autowired
    private TimetableService timetableService;

    @GetMapping
    public ResponseEntity<?> getWeeklyTimetable(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "week", required = false) Integer week,
            @RequestParam(value = "tz", required = false, defaultValue = "Europe/London") String timezone) {

        try {
            // Validate year and week
            if (year == null || week == null) {
                return ResponseEntity.badRequest().body("Year and week parameters are required.");
            }

            // Fetch timetable from the service
            Object timetable = timetableService.getTimetable(year, week, timezone);
            return ResponseEntity.ok(timetable);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch timetable data.");
        }
    }

    @GetMapping("/{airType}")
    public ResponseEntity<?> getWeeklyTimetableByAirType(
            @PathVariable String airType,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "week", required = false) Integer week,
            @RequestParam(value = "tz", required = false, defaultValue = "Europe/London") String timezone) {

        try {
            // Validate airType
            if (!"raw".equals(airType) && !"sub".equals(airType) && !"dub".equals(airType) && !"all".equals(airType)) {
                return ResponseEntity.badRequest().body("Invalid airType value.");
            }

            // Validate year and week
            if (year == null || week == null) {
                return ResponseEntity.badRequest().body("Year and week parameters are required.");
            }

            // Fetch timetable from the service
            Object timetable = timetableService.getTimetableByAirType(airType, year, week, timezone);
            return ResponseEntity.ok(timetable);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch timetable data.");
        }
    }
}
