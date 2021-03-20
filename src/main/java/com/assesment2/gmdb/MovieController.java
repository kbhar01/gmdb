package com.assesment2.gmdb;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/gmdb")
public class MovieController {

    List<MovieDTO> listOfMovies = new ArrayList<MovieDTO>();

    @RequestMapping(value="/movies", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovie(@RequestBody MovieDTO movieDTO) {
            this.listOfMovies.add(movieDTO);

    }

}
