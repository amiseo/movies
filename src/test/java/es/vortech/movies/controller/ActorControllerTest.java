package es.vortech.movies.controller;

import es.vortech.movies.MoviesApplication;
import es.vortech.movies.entity.Actor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MoviesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActorControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void retrieveActorsSuccessfully() {

        ResponseEntity<Actor[]> response = restTemplate.getForEntity("/api/actors", Actor[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().length).isEqualTo(0);
    }
}