package org.example.cinema.service.impl.tu;

import org.example.cinema.dto.MovieDetail;
import org.example.cinema.entity.Movie;
import org.example.cinema.repository.MovieRepository;
import org.example.cinema.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springdoc.core.service.AbstractRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // better test e2e => all components
// TODO: mock without Spring
class MovieServiceJpaTest {

    // component to test
    @Autowired
    MovieService movieService;

    // mock component
    @MockitoBean
    MovieRepository movieRepositoryMocked;

    @Autowired
    private AbstractRequestService abstractRequestService;
    @Autowired
    private MovieRepository movieRepository;

    @Test
    void testGetById_whenPresent(){
        // given
        int movieId = 123456;
        // prepare mock
        Movie movieEntityMocked = Movie.builder()
                .id(movieId)
                .title("movie mocked")
                .releaseYear(1950)
                // TODO: values of other fields
                .build();
        Mockito.when(movieRepositoryMocked.findById(Mockito.eq(movieId)))
                .thenReturn(Optional.of(movieEntityMocked));

        // when
        Optional<MovieDetail> optionalMovieDetail = movieService.getById(movieId);

        // then
        assertTrue(optionalMovieDetail.isPresent(), "movie found");
        var movieDetailed = optionalMovieDetail.get();
        assertAll(
                () -> assertEquals(movieId, movieDetailed.getId()),
                () -> assertEquals(movieEntityMocked.getTitle(), movieDetailed.getTitle()),
                () -> assertEquals(movieEntityMocked.getReleaseYear(), movieDetailed.getYear())
                // TODO : check mapping other fields
        );

        // verify mock repository
        BDDMockito.then(movieRepository)
                .should()
                .findById(Mockito.eq(movieId));
    }

    @Test
    void testGetById_whenAbsent(){
        // given
        int movieId = 654321;
        // prepare mock : default answer is Optional.empty()
        // when
        Optional<MovieDetail> optionalMovieDetail = movieService.getById(movieId);
        // then
        assertTrue(optionalMovieDetail.isEmpty(), "movie not found");
        // verify mock repository
        BDDMockito.then(movieRepository)
                .should()
                .findById(Mockito.eq(movieId));
    }


}