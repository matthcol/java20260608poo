package org.example.cinema.service.impl;


import jakarta.transaction.Transactional;
import org.example.cinema.dto.MovieCreate;
import org.example.cinema.dto.MovieDetail;
import org.example.cinema.dto.MovieSimple;
import org.example.cinema.entity.Movie;
import org.example.cinema.repository.MovieRepository;
import org.example.cinema.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Spring component
public class MovieServiceJpa implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional // NB: rollback auto if any exception, otherwise commit
    @Override
    public MovieSimple create(MovieCreate movieCreate) {
//        var movieEntity = Movie.builder()
//                .title(movieCreate.getTitle())
//                .releaseYear(movieCreate.getYear())
//                .duration(movieCreate.getDuration())
//                .build();
        Movie movieEntity = modelMapper.map(movieCreate, Movie.class);
        movieRepository.saveAndFlush(movieEntity);
//        var movieSimple = new MovieSimple();
//        movieSimple.setId(movieEntity.getId());
//        movieSimple.setTitle(movieEntity.getTitle());
//        movieSimple.setYear(movieEntity.getReleaseYear());
//        movieSimple.setDuration(movieEntity.getDuration());
        MovieSimple movieSimple = modelMapper.map(movieEntity, MovieSimple.class);
        return movieSimple;
    }

    @Override
    public Optional<MovieDetail> getById(int movieId) {
        Optional<Movie> optMovieEntity = movieRepository.findById(movieId);
        Optional<MovieDetail> optMovieDetail = optMovieEntity.map(
                movieEntity -> {
                    var movieDetail = new MovieDetail();
                    movieDetail.setId(movieEntity.getId());
                    movieDetail.setTitle(movieEntity.getTitle());
                    movieDetail.setYear(movieEntity.getReleaseYear());
                    // TODO: map other fields + actors + director
                    return movieDetail;
                }
        );
        return optMovieDetail;
    }

    @Override
    public List<MovieSimple> getAll() {
        return List.of();
    }

    @Override
    public List<MovieSimple> getByYear(int year) {
        return List.of();
    }
}
