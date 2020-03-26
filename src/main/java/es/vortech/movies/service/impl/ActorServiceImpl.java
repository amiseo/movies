package es.vortech.movies.service.impl;

import es.vortech.movies.entity.Actor;
import es.vortech.movies.repository.ActorRepository;
import es.vortech.movies.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository repository;

    @Override
    public Iterable<Actor> retrieveActors() {
        return repository.findAll();
    }
}
