package es.vortech.movies.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class MovieDto {

    private String title;
    private Integer year;
}
