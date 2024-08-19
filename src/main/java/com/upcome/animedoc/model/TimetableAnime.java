package com.upcome.animedoc.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class TimetableAnime {
    private String title;
    private String route;
    private String romaji;
    private String english;
    private String nativeName;
    private String delayedText;
    private LocalDateTime delayedFrom;
    private LocalDateTime delayedUntil;
    private String status;
    private LocalDateTime episodeDate;
    private int episodeNumber;
    private int subtractedEpisodeNumber;
    private int episodes;
    private int lengthMin;
    private boolean donghua;
    private String airType;
    private List<Category> mediaTypes;
    private String imageVersionRoute;
    private Streams streams;
    private String airingStatus;
}
