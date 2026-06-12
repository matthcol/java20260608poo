package org.example.cinema.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class MovieSimple extends MovieCreate {
    private int id;
}
