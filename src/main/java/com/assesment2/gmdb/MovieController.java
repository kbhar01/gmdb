package com.assesment2.gmdb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> getMovieDetails(@PathVariable("title") String title) throws JsonProcessingException {
        Optional<MovieDTO> dtoObj =  this.movieService.fetchAll().stream().filter(
                movieDTO -> movieDTO.getTitle().equals(title)
        ).findFirst();
        if( dtoObj.equals(Optional.empty())) {
            return new ResponseEntity<>("Movie with {title} does not exist", HttpStatus.NO_CONTENT);
        } else {
            ObjectMapper objectMapper = new ObjectMapper();

            return new ResponseEntity<>(objectMapper.writeValueAsString(dtoObj.get()), HttpStatus.OK);
        }
    }

}
