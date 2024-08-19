package com.upcome.animedoc.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Anime {
    private String id;
    private String title;
    private String route;
    private LocalDateTime premier;
    private LocalDateTime subPremier;
    private LocalDateTime dubPremier;
    private String month;
    private int year;
    private Season season;
    private String delayedTimetable;
    private LocalDateTime delayedFrom;
    private LocalDateTime delayedUntil;
    private String subDelayedTimetable;
    private LocalDateTime subDelayedFrom;
    private LocalDateTime subDelayedUntil;
    private String dubDelayedTimetable;
    private LocalDateTime dubDelayedFrom;
    private LocalDateTime dubDelayedUntil;
    private String delayedDesc;
    private LocalDateTime jpnTime;
    private LocalDateTime subTime;
    private LocalDateTime dubTime;
    private String description;
    private List<Category> genres;
    private List<Category> studios;
    private List<Category> sources;
    private List<Category> mediaTypes;
    private int episodes;
    private int lengthMin;
    private String status;
    private String imageVersionRoute;
    private Stats stats;
    private Days days;
    private Names names;
    private Relations relations;
    private Websites websites;
}
