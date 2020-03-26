package es.vortech.movies.controller;

import es.vortech.movies.entity.Actor;
import es.vortech.movies.entity.Movie;
import es.vortech.movies.service.MovieService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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
    public ResponseEntity<Iterable<Movie>> retrieveActors() {
        return new ResponseEntity<>(service.retrieveMovies(), HttpStatus.OK);
    }
}
