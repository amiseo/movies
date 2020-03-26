package es.vortech.movies.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.vortech.movies.MoviesApplication;
import es.vortech.movies.entity.Actor;
import es.vortech.movies.entity.Movie;
import es.vortech.movies.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MoviesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MovieRepository repository;

    @Test
    void saveSuccessfully() {
        // Given
        String request = "{\n" +
                "  \"actors\": [\n" +
                "    {\n" +
                "      \"id\": 1    }\n" +
                "  ],\n" +
                "  \"oscars\": 2,\n" +
                "  \"title\": \"string\",\n" +
                "  \"year\": 2020\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //When
        ResponseEntity<Void> response = restTemplate.exchange("/api/movies", HttpMethod.POST, new
                HttpEntity<>(request, headers), Void.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().containsKey("location")).isTrue();
    }

    @Test
    void saveFailedByActor() {
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
    void saveFailedByYear() {
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

    @Test
    void retrieveEmpty() {
        //Given
        repository.deleteAll();

        //When
        ResponseEntity<Movie[]> response = restTemplate.getForEntity("/api/movies", Movie[].class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().length).isEqualTo(0);
    }

    @Test
    void retrieveList() {
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
        repository.save(movie);

        //When
        ResponseEntity<Movie[]> response = restTemplate.getForEntity("/api/movies", Movie[].class);


        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().length).isEqualTo(1);
    }


}