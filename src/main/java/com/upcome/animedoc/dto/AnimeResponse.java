package com.upcome.animedoc.dto;
import com.upcome.animedoc.model.Anime;

import java.util.List;

public class AnimeResponse {
    private int page;
    private int totalAmount;
    private List<Anime> anime;

    // Getters and setters
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Anime> getAnime() {
        return anime;
    }

    public void setAnime(List<Anime> anime) {
        this.anime = anime;
    }
}

