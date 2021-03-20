package com.assesment2.gmdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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


    public List<MovieDTO> fetchAll() {
        return movieRepository.findAll()
                .stream()
                .map(movieEntity -> {
                    return new MovieDTO(movieEntity.getMovieName());
                })
                .collect(Collectors.toList());

    }
}
