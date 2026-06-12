package org.example.cinema.config;

import org.example.cinema.dto.MovieCreate;
import org.example.cinema.dto.MovieSimple;
import org.example.cinema.entity.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper(){
        var modelMapper = new ModelMapper();
        modelMapper.typeMap(MovieCreate.class, Movie.class)
                .addMapping(MovieCreate::getYear, Movie::setReleaseYear);
        modelMapper.typeMap(Movie.class, MovieSimple.class)
                .addMapping(Movie::getReleaseYear, MovieSimple::setYear);
        return modelMapper;
    }
}
