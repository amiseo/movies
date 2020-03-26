package es.vortech.movies.service;

import es.vortech.movies.entity.Movie;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface MovieService {

    Movie save(@NotNull @Valid Movie movie);

    Iterable<Movie> retrieveMovies();
}
