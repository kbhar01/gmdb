package com.assesment2.gmdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void create(MovieDTO movieDto) {
        movieRepository.save(
                new MovieEntity(movieDto.getMovieName())
        );
    }


}
