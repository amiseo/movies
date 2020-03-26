package es.vortech.movies.service.impl;

import es.vortech.movies.entity.Actor;
import es.vortech.movies.entity.Movie;
import es.vortech.movies.repository.MovieRepository;
import es.vortech.movies.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {MovieServiceImplTest.Config.class})
@RunWith(SpringRunner.class)
public class MovieServiceImplTest {

    @Autowired
    private MovieService service;


    @Configuration
    public static class Config {

        @MockBean
        public static MovieRepository repository;

        @Bean
        public MovieService service() {
            return new MovieServiceImpl(repository);
        }
    }

    @Test
    public void saveSuccessfully() {
        //Given
        Movie movie = new Movie();
        movie.setOscars(0);
        movie.setTitle("Covid World Tour 2020");
        movie.setYear(2020);
        movie.setActors(new ArrayList<>());
        Actor will = new Actor();
        will.setId(1L);
        will.setName("Will Smith");
        movie.getActors().add(will);

        Movie response = new Movie();
        response.setId(1L);

        Mockito.when(Config.repository.save(movie)).thenReturn(response);

        //When
        Movie result = service.save(movie);

        //Then
        assertThat(result.getId()).isNotNull();
    }
}