package com.assesment2.gmdb;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
public class MovieDTO {
    String title;
    String director;
    String actors;
    String release;
    String description;
    String rating;

    MovieDTO(String title) {
        this.title = title;
    }

    public MovieDTO(String title,
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
