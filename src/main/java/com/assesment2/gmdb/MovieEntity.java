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

    String movieName;

    MovieEntity(String movieName) {
        this.movieName = movieName;
    }
}
