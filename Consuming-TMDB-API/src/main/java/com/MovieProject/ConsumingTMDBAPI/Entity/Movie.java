package com.MovieProject.ConsumingTMDBAPI.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String releaseDate;
    private String language;
    @Column(name = "overview", length = 1000)
    private String overview;
    private double voteAverage;

    public Movie(String name, String releaseDate, String language, String overview, double voteAverage) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.language = language;
        this.overview = overview;
        this.voteAverage = voteAverage;
    }
}
