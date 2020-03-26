package es.vortech.movies.controller;

import es.vortech.movies.entity.Actor;
import es.vortech.movies.service.ActorService;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/actors", produces = MediaType.APPLICATION_JSON_VALUE)
@SwaggerDefinition
public class ActorController {

    private final ActorService service;

    public ActorController(ActorService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Actor>> retrieveActors() {
        return new ResponseEntity<>(service.retrieveActors(), HttpStatus.OK);
    }
}
