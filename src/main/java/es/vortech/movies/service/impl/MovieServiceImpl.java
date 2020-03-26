package es.vortech.movies.service.impl;

import es.vortech.movies.entity.Movie;
import es.vortech.movies.repository.MovieRepository;
import es.vortech.movies.service.MovieService;
import org.springframework.stereotype.Service;

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
}
