package com.assesment2.gmdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/gmdb")
public class MovieController {

    MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService)
    {
        this.movieService = movieService;
    }


    @RequestMapping(value="/movies", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<MovieDTO> getMovies() {
        return this.movieService.fetchAll();
    }

    @RequestMapping(value="/movies", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovie(@RequestBody List<MovieDTO> movieDTOs) {
        movieDTOs.forEach(movieDTO -> this.movieService.create(movieDTO) );
    }

    @RequestMapping(value="/movies/{title}", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Optional<MovieDTO> getMovieDetails(@PathVariable("title") String title) {
        return this.movieService.fetchAll().stream().filter(
                movieDTO -> movieDTO.getTitle().equals(title)
        ).findFirst();
    }

}
