package org.example.cinema.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class MovieDetail extends MovieSimple {
    private String director;
    private List<String> actors;
}
