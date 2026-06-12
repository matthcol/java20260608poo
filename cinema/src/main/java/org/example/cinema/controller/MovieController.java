package org.example.cinema.controller;

import jakarta.validation.Valid;
import org.example.cinema.annotation.Note;
import org.example.cinema.dto.MovieCreate;
import org.example.cinema.dto.MovieDetail;
import org.example.cinema.dto.MovieSimple;
import org.example.cinema.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;
import java.util.List;

@RestController // Spring component => Servlet JEE
@RequestMapping("/api/movie")
public class MovieController {

    private Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired // DI
    private MovieService movieService;

    @Note
    @GetMapping
    public List<MovieSimple> getMovies(){
        // @Note : compilation error
        var movies = movieService.getAll();
        logger.debug("Get all movies: {}", movies.size());
        return movies;
    }

    @GetMapping("/{movieId}")
    public MovieDetail getById(int movieId){
        return movieService.getById(movieId)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                MessageFormat.format("Movie {0} not found", movieId)
                        )
                );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MovieSimple createMovie(@RequestBody @Valid MovieCreate movieCreate){
        logger.debug("Add movie: {}", movieCreate);
        return movieService.create(movieCreate);
    }
}
