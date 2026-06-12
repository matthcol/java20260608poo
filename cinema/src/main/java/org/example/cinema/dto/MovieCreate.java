package org.example.cinema.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// lombok
@Getter
@Setter
@ToString
public class MovieCreate {
    @NotBlank
    private String title;

    @NotNull
    @Min(1850)
    private Integer year;

    private Integer duration;
}
