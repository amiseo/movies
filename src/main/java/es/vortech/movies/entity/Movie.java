package es.vortech.movies.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonAutoDetect
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonIgnore
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer oscars;

    @NotEmpty
    @OneToMany
    @JoinColumn(name = "actor_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Actor> actors;

}
