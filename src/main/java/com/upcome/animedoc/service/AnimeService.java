package com.upcome.animedoc.service;

import com.upcome.animedoc.dto.AnimeResponse;
import com.upcome.animedoc.model.Anime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimeService {

    private final RestTemplate restTemplate;
    @Value("${api.base.url}")
    private String BASE_URL;

    public AnimeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Anime> getAllAnimes(int page, String query, String matchType, String sortType, List<String> genres, List<String> genresExclude,
                                    List<String> studios, List<String> studiosExclude, List<String> sources, List<String> sourcesExclude,
                                    List<String> mediaTypes, List<String> mediaTypesExclude, List<Integer> years, List<Integer> yearsExclude,
                                    List<String> seasons, List<String> seasonsExclude, List<String> airingStatuses, List<String> airingStatusesExclude,
                                    String duration, String episodes, List<String> streams, List<String> streamsExclude, List<Integer> malIds,
                                    List<Integer> anilistIds, List<Integer> anidbIds) {

        String finalUrl = buildUrl(page, query, matchType, sortType, genres, genresExclude, studios, studiosExclude, sources, sourcesExclude,
                mediaTypes, mediaTypesExclude, years, yearsExclude, seasons, seasonsExclude, airingStatuses, airingStatusesExclude,
                duration, episodes, streams, streamsExclude, malIds, anilistIds, anidbIds);

        // Fetch data from the API
        ResponseEntity<AnimeResponse> response = restTemplate.exchange(finalUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<AnimeResponse>() {});

        return response.getBody().getAnime();
    }

    public Anime getAnimeBySlug(String slug) {
        String url = BASE_URL + slug;

        try {
            ResponseEntity<Anime> response = restTemplate.getForEntity(url, Anime.class);
            return response.getBody();
        } catch (Exception e) {
            // Handle exceptions
            throw new RuntimeException("Failed to fetch anime data", e);
        }
    }


    private String buildUrl(int page, String query, String matchType, String sortType, List<String> genres, List<String> genresExclude,
                            List<String> studios, List<String> studiosExclude, List<String> sources, List<String> sourcesExclude,
                            List<String> mediaTypes, List<String> mediaTypesExclude, List<Integer> years, List<Integer> yearsExclude,
                            List<String> seasons, List<String> seasonsExclude, List<String> airingStatuses, List<String> airingStatusesExclude,
                            String duration, String episodes, List<String> streams, List<String> streamsExclude, List<Integer> malIds,
                            List<Integer> anilistIds, List<Integer> anidbIds) {

        StringBuilder urlBuilder = new StringBuilder(BASE_URL);

        urlBuilder.append("page=").append(page);

        appendParam(urlBuilder, "q", query);
        appendParam(urlBuilder, "mt", matchType);
        appendParam(urlBuilder, "st", sortType);
        appendParam(urlBuilder, "genres", genres);
        appendParam(urlBuilder, "genres-exclude", genresExclude);
        appendParam(urlBuilder, "studios", studios);
        appendParam(urlBuilder, "studios-exclude", studiosExclude);
        appendParam(urlBuilder, "sources", sources);
        appendParam(urlBuilder, "sources-exclude", sourcesExclude);
        appendParam(urlBuilder, "media-types", mediaTypes);
        appendParam(urlBuilder, "media-types-exclude", mediaTypesExclude);
        appendParam(urlBuilder, "years", years);
        appendParam(urlBuilder, "years-exclude", yearsExclude);
        appendParam(urlBuilder, "seasons", seasons);
        appendParam(urlBuilder, "seasons-exclude", seasonsExclude);
        appendParam(urlBuilder, "airing-statuses", airingStatuses);
        appendParam(urlBuilder, "airing-statuses-exclude", airingStatusesExclude);
        appendParam(urlBuilder, "duration", duration);
        appendParam(urlBuilder, "episodes", episodes);
        appendParam(urlBuilder, "streams", streams);
        appendParam(urlBuilder, "streams-exclude", streamsExclude);
        appendParam(urlBuilder, "mal-ids", malIds);
        appendParam(urlBuilder, "anilist-ids", anilistIds);
        appendParam(urlBuilder, "anidb-ids", anidbIds);

        return urlBuilder.toString();
    }

    private void appendParam(StringBuilder urlBuilder, String key, Object value) {
        if (value != null) {
            if (value instanceof List) {
                List<?> list = (List<?>) value;
                if (!list.isEmpty()) {
                    String paramValue = list.stream()
                            .map(Object::toString)
                            .collect(Collectors.joining("&" + key + "="));
                    urlBuilder.append("&").append(key).append("=").append(paramValue);
                }
            } else {
                urlBuilder.append("&").append(key).append("=").append(value);
            }
        }
    }

}
