package es.vortech.movies.controller;

import es.vortech.movies.MoviesApplication;
import es.vortech.movies.entity.Actor;
import es.vortech.movies.entity.Movie;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MoviesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void saveSuccessfully() {
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

        //When
        ResponseEntity<Void> response = restTemplate.postForEntity("/api/movies", movie, Void.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().containsKey("location")).isTrue();
    }

    @Test
    void saveFailed() {
        //Given
        Movie movie = new Movie();
        movie.setOscars(0);
        movie.setTitle("Covid World Tour 2020");
        movie.setYear(2020);

        //When
        ResponseEntity<Void> response = restTemplate.postForEntity("/api/movies", movie, Void.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getHeaders().containsKey("location")).isFalse();
    }

    @Test
    void saveFailed2() {
        //Given
        Movie movie = new Movie();
        movie.setOscars(0);
        movie.setTitle("Covid World Tour 2020");
        movie.setYear(123);
        movie.setActors(new ArrayList<>());
        Actor will = new Actor();
        will.setId(1L);
        will.setName("Will Smith");
        movie.getActors().add(will);

        //When
        ResponseEntity<Void> response = restTemplate.postForEntity("/api/movies", movie, Void.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getHeaders().containsKey("location")).isFalse();
    }

}