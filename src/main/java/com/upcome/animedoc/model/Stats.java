package com.upcome.animedoc.model;

import lombok.Data;

@Data
public class Stats {
    private double averageScore;
    private int ratingCount;
    private int trackedCount;
    private int trackedRating;
    private int popularityRating;
    private String colorLightMode;
    private String colorDarkMode;
}
