package com.MovieProject.ConsumingTMDBAPI.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MovieProject.ConsumingTMDBAPI.Entity.Movie;
import com.MovieProject.ConsumingTMDBAPI.Service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/fetch-and-store")
    public String fetchAndStoreAllMovies() {
        List<Movie> movies = movieService.fetchAllMovieDataFromTMDB();
        movieService.saveAllMovies(movies);
        return "Fetched and stored " + movies.size() + " movies in the database.";
    }

    @GetMapping("/list")
    public List<Movie> listAllMovies() {
        return movieService.getAllMovies();
    }

    // Add other methods for rating movies, etc.
}
