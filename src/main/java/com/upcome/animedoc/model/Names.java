package com.upcome.animedoc.model;

import lombok.Data;

import java.util.List;

@Data
public class Names {
    private String romaji;
    private String english;
    private String nativeName;
    private String abbreviation;
    private List<String> synonyms;
}
