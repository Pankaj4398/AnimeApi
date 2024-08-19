package com.upcome.animedoc.model;

import lombok.Data;

import java.util.List;

@Data
public class Relations {
    private List<String> sequels;
    private List<String> prequels;
    private List<String> parents;
    private List<String> alternatives;
    private List<String> other;
    private List<String> sideStories;
    private List<String> spinoffs;
}
