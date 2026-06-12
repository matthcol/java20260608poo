package org.example.cinema.repository.demo;

import org.example.cinema.annotation.Note;
import org.example.cinema.entity.Movie;
import org.example.cinema.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@Note("@DataJpaTest is an extension of JUnit initializing Hibernate and repositories")
@DataJpaTest
@Sql
class MovieRepositoryTest {

    @Autowired // DI : dependency injection
    MovieRepository movieRepository;

    // @Rollback(false) // debug on real db
    @Test
    void demoCreateMovie(){
        var movie = Movie.builder()
                .title("The Mandalorian and Grogu")
                .releaseYear(2026)
                .build();
        System.out.println(movie);

        movieRepository.saveAndFlush(movie);
        System.out.println(movie);
    }

    @Test
    void demoFindAll(){
        movieRepository.findAll()
                .forEach(System.out::println);
    }

    @ParameterizedTest
    @ValueSource(ints={1, 5, 9, 13, 17, 666})
    void demoFindById(int movieId)
    {
        var optMovie = movieRepository.findById(movieId);
        optMovie.ifPresentOrElse(
                movie -> System.out.println("Movie found: " + movie),
                () -> System.out.println("Movie not found with id: " + movieId)
        );
    }

    @Test
    void demoIntrospection(){
        var currentClass = getClass();
        var currentPackage = currentClass.getPackage();
        System.out.println("class: " + currentClass);
        System.out.println("package: " + currentPackage);
        var annotations = currentClass.getAnnotations();
        Arrays.stream(annotations)
                .forEach(a -> System.out.println("annotation: " + a));
    }

    @Test
    void demoFindByYear(){
        var movies = movieRepository.findByReleaseYear(1997);
        movies.forEach(System.out::println);
    }

    @Test
    void demoSearch(){
        var movies = movieRepository.search(1997, "Snatch");
        movies.forEach(System.out::println);
    }

}