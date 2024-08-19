package com.upcome.animedoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.upcome.animedoc.model.Anime;
import com.upcome.animedoc.service.AnimeService;

@RestController
@RequestMapping("/api/anime")
public class AnimeController {

    @Autowired
    private AnimeService animeService;

    @GetMapping("/animes")
    public ResponseEntity<List<Anime>> getAnimes(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "mt", defaultValue = "all") String matchType,
            @RequestParam(value = "st", defaultValue = "popularity") String sortType,
            @RequestParam(value = "genres", required = false) List<String> genres,
            @RequestParam(value = "genres-exclude", required = false) List<String> genresExclude,
            @RequestParam(value = "studios", required = false) List<String> studios,
            @RequestParam(value = "studios-exclude", required = false) List<String> studiosExclude,
            @RequestParam(value = "sources", required = false) List<String> sources,
            @RequestParam(value = "sources-exclude", required = false) List<String> sourcesExclude,
            @RequestParam(value = "media-types", required = false) List<String> mediaTypes,
            @RequestParam(value = "media-types-exclude", required = false) List<String> mediaTypesExclude,
            @RequestParam(value = "years", required = false) List<Integer> years,
            @RequestParam(value = "years-exclude", required = false) List<Integer> yearsExclude,
            @RequestParam(value = "seasons", required = false) List<String> seasons,
            @RequestParam(value = "seasons-exclude", required = false) List<String> seasonsExclude,
            @RequestParam(value = "airing-statuses", required = false) List<String> airingStatuses,
            @RequestParam(value = "airing-statuses-exclude", required = false) List<String> airingStatusesExclude,
            @RequestParam(value = "duration", required = false) String duration,
            @RequestParam(value = "episodes", required = false) String episodes,
            @RequestParam(value = "streams", required = false) List<String> streams,
            @RequestParam(value = "streams-exclude", required = false) List<String> streamsExclude,
            @RequestParam(value = "mal-ids", required = false) List<Integer> malIds,
            @RequestParam(value = "anilist-ids", required = false) List<Integer> anilistIds,
            @RequestParam(value = "anidb-ids", required = false) List<Integer> anidbIds
    ) {
        try {
            List<Anime> animeList = animeService.getAllAnimes(page, query, matchType, sortType, genres, genresExclude, studios, studiosExclude, sources, sourcesExclude,
                    mediaTypes, mediaTypesExclude, years, yearsExclude, seasons, seasonsExclude, airingStatuses, airingStatusesExclude,
                    duration, episodes, streams, streamsExclude, malIds, anilistIds, anidbIds);
            if (animeList != null) {
                return ResponseEntity.ok(animeList);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            // Handle exceptions, such as connectivity issues or parsing errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{slug}")
    public ResponseEntity<Anime> getAnimeBySlug(@PathVariable String slug) {
        try {
            Anime anime = animeService.getAnimeBySlug(slug);
            if (anime != null) {
                return ResponseEntity.ok(anime);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            // Handle exceptions, such as connectivity issues or parsing errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
