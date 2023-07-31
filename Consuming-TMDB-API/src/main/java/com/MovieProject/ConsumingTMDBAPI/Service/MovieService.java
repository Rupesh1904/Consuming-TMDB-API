package com.MovieProject.ConsumingTMDBAPI.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.MovieProject.ConsumingTMDBAPI.Entity.Movie;
import com.MovieProject.ConsumingTMDBAPI.Repository.MovieRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class MovieService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${tmdb.apiKey}")
    private String tmdbApiKey;

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> fetchAllMovieDataFromTMDB() {
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + tmdbApiKey;
        ResponseEntity<ObjectNode> response = restTemplate.exchange(url, HttpMethod.GET, null, ObjectNode.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            ObjectNode jsonResponse = response.getBody();
            return mapToMovieEntities(jsonResponse);
        } else {
            // Handle error or return an empty list
            return Collections.emptyList();
        }
    }

    private List<Movie> mapToMovieEntities(ObjectNode jsonResponse) {
        List<Movie> movies = new ArrayList<>();
        ArrayNode results = (ArrayNode) jsonResponse.get("results");
        for (JsonNode result : results) {
            Movie movie = new Movie();
            movie.setName(result.get("title").asText());
            movie.setReleaseDate(result.get("release_date").asText());
            movie.setLanguage(result.get("original_language").asText());
            movie.setOverview(result.get("overview").asText());
            movie.setVoteAverage(result.get("vote_average").asDouble());
            movies.add(movie);
        }
        return movies;
    }

    public void saveAllMovies(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Add other methods for rating movies, etc.
}
