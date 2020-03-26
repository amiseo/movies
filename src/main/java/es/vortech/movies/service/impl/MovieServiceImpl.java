package es.vortech.movies.service.impl;

import es.vortech.movies.dto.MovieDto;
import es.vortech.movies.entity.Movie;
import es.vortech.movies.repository.MovieRepository;
import es.vortech.movies.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    public MovieServiceImpl(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public Movie save(Movie movie) {
        return repository.save(movie);
    }

    @Override
    public List<MovieDto> retrieveMovies() {
        Iterable<Movie> movies = repository.findAll();
        List<MovieDto> dtos =  StreamSupport.stream(movies.spliterator(), false)
                .map(m -> new MovieDto(m.getTitle(), m.getYear()))
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    public Optional<Movie> retrieveMovie(Long id) {
        return repository.getById(id);
    }

}
