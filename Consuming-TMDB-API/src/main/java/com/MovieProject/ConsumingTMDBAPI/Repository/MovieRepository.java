package com.MovieProject.ConsumingTMDBAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MovieProject.ConsumingTMDBAPI.Entity.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long> {
    // You can add custom query methods here if needed

}