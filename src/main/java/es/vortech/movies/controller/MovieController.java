package es.vortech.movies.controller;

import es.vortech.movies.dto.MovieDto;
import es.vortech.movies.entity.Actor;
import es.vortech.movies.entity.Movie;
import es.vortech.movies.service.MovieService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@SwaggerDefinition
@Api(value="Movie Management")
@RequestMapping(value = "/api/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private final MovieService service;

    @Value("#{servletContext.contextPath}")
    private String servletContextPath;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @ApiOperation(value = "Storage a movie in the app", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Missing parameter"),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody Movie request) {
        Movie movie = service.save(request);
        return ResponseEntity.created(URI.create(servletContextPath.concat("/").concat(movie.getId().toString())))
                .build();
    }

    @ApiOperation(value = "Return all movies")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<MovieDto>> retrieveMovies() {
        return new ResponseEntity<>(service.retrieveMovies(), HttpStatus.OK);
    }

    @ApiOperation(value = "Return specific movie")
    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Movie no found")
    })
    public ResponseEntity<Movie> retrieveMovie(@PathVariable("id") Long id) {
        Optional<Movie> movie = service.retrieveMovie(id);
        return movie.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
