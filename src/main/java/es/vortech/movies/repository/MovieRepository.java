package es.vortech.movies.repository;

import es.vortech.movies.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
}
