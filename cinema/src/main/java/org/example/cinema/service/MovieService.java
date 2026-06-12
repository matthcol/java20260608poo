package org.example.cinema.service;

import org.example.cinema.dto.MovieCreate;
import org.example.cinema.dto.MovieDetail;
import org.example.cinema.dto.MovieSimple;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    MovieSimple create(MovieCreate movieCreate);
    Optional<MovieDetail> getById(int movieId);
    List<MovieSimple> getAll(); // TODO: pagination
    List<MovieSimple> getByYear(int year);

}
