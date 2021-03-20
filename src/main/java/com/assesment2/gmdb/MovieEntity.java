package com.assesment2.gmdb;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;

    String title;
    String director;
    String actors;
    String release;
    String description;
    String rating;

    MovieEntity(String title) {
        this.title = title;
    }

    public MovieEntity(String title,
                       String director,
                       String actors,
                       String release,
                       String description,
                       String rating) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.release = release;
        this.description = description;
        this.rating = rating;
    }
}
