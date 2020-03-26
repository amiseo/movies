package es.vortech.movies.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "movie")
@Data @NoArgsConstructor
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "title")
    private String title;

    @NotNull
    @Min(1800)
    @Column(name = "year")
    private Integer year;

    @Min(0)
    @Column(name = "oscars")
    private Integer oscars;

    @NotEmpty
    @OneToMany
    @JoinColumn(name = "actor_id")
    private List<Actor> actors;

}
