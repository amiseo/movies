package es.vortech.movies.service;

import es.vortech.movies.dto.MovieDto;
import es.vortech.movies.entity.Movie;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Validated
public interface MovieService {

    Movie save(@NotNull @Valid Movie movie);

    List<MovieDto> retrieveMovies();

    Optional<Movie> retrieveMovie(Long id);
}
