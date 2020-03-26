package es.vortech.movies.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "actor")
@Data
@NoArgsConstructor
public class Actor {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

}
