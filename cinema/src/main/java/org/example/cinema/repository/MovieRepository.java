package org.example.cinema.repository;

import org.example.cinema.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByReleaseYear(int year);

    @Query("""
        SELECT m
        FROM Movie m
        WHERE m.releaseYear = :year
            OR m.title LIKE :titlePart
        ORDER BY m.releaseYear DESC
    """)
    List<Movie> search(int year, String titlePart);

}
