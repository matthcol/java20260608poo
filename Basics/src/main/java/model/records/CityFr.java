package model.records;

import lombok.NonNull;

// Java 17 : non mutable objects
public record CityFr(
        String codeInsee,
        String name,
        int population,
        int area
) {
}
