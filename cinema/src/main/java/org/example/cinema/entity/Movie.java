package org.example.cinema.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.example.cinema.annotation.Note;

// JPA : Java Persistence API (ORM)
// Java SE JDBC : Java DataBase Connectivity (SQL)
@Entity
// lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(of={"id", "title", "releaseYear"})
// custom
@Note("persistent entity mapped to table movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // nullable (create)

    private String title;

    private int releaseYear;

    @Note(value = "this field is nullable", level = Note.Level.IMPORTANT)
    private Integer duration;
}
