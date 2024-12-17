package com.example.demo;

import com.example.demo.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${omdb.api.key}")
    private String apiKey;

    private static final String OMDB_API_URL = "http://www.omdbapi.com/?t={title}&apikey={apikey}";

    public Movie getMovieByTitle(String title) {
        return restTemplate.getForObject(OMDB_API_URL, Movie.class, title, apiKey);
    }
}
