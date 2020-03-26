package es.vortech.movies.repository;

import es.vortech.movies.entity.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    @EntityGraph(attributePaths = {"actors"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Movie> getById(Long id);

}
